const app = document.querySelector('#app');
const notice = document.querySelector('#notice');
let currentPost = null;

function updatePath(path) {
  if (location.pathname !== path) history.pushState({}, '', path);
}

async function api(url, options = {}) {
  const response = await fetch(url, {
    ...options,
    headers: { 'Content-Type': 'application/json', ...(options.headers || {}) }
  });
  if (!response.ok) {
    const error = await response.json().catch(() => ({}));
    throw new Error(error.message || `요청 처리에 실패했습니다. (${response.status})`);
  }
  return response.status === 204 ? null : response.json();
}

function escapeHtml(value = '') {
  return String(value).replace(/[&<>'"]/g, char => ({'&':'&amp;','<':'&lt;','>':'&gt;',"'":'&#39;','"':'&quot;'}[char]));
}

function formatDate(value) {
  if (!value) return '-';
  return new Intl.DateTimeFormat('ko-KR', { dateStyle:'medium', timeStyle:'short' }).format(new Date(value));
}

function renderCommentTime(comment) {
  const created = formatDate(comment.createdDate);
  if (!comment.updatedDate) return created;
  const createdTime = new Date(comment.createdDate).getTime();
  const updatedTime = new Date(comment.updatedDate).getTime();
  const updated = updatedTime > createdTime + 1000
    ? `<small class="updated-time">수정: ${formatDate(comment.updatedDate)}</small>`
    : '';
  return `${created}${updated}`;
}

function showNotice(message) {
  notice.textContent = message;
  notice.classList.add('show');
  clearTimeout(showNotice.timer);
  showNotice.timer = setTimeout(() => notice.classList.remove('show'), 2600);
}

async function showList(updateHistory = true) {
  if (updateHistory) updatePath('/');
  app.innerHTML = '<div class="loading">게시글을 불러오는 중입니다.</div>';
  try {
    const posts = await api('/api/post');
    app.innerHTML = `
      <section class="toolbar"><div><h2>게시글</h2><span class="subtitle">자유롭게 이야기를 나눠보세요.</span></div><button class="btn" onclick="showPostForm()">새 글 작성</button></section>
      <section class="card post-list">
        <div class="post-row head"><span>번호</span><span>제목</span><span>작성자</span><span>작성일</span></div>
        ${posts.length ? posts.map(post => `
          <div class="post-row" onclick="showDetail(${post.id})">
            <span class="meta">${post.id}</span>
            <span class="title">${escapeHtml(post.title)}<span class="count">${post.commentList?.length ? `[${post.commentList.length}]` : ''}</span></span>
            <span>${escapeHtml(post.writer)}</span><span class="meta">${formatDate(post.createdDate)}</span>
          </div>`).join('') : '<div class="empty">아직 작성된 게시글이 없습니다.</div>'}
      </section>`;
  } catch (error) { renderError(error); }
}

async function showDetail(postId, updateHistory = true) {
  if (updateHistory) updatePath(`/posts/${postId}`);
  app.innerHTML = '<div class="loading">게시글을 불러오는 중입니다.</div>';
  try {
    currentPost = await api(`/api/post/${postId}`);
    app.innerHTML = `
      <div class="toolbar"><button class="btn secondary" onclick="showList()">목록으로</button><div class="actions"><button class="btn secondary" onclick="showPostForm(true)">수정</button><button class="btn danger" onclick="deletePost(${postId})">삭제</button></div></div>
      <article class="card detail-card">
        <span class="meta">게시글 #${currentPost.id}</span><h2 class="detail-title">${escapeHtml(currentPost.title)}</h2>
        <div class="detail-meta"><strong>${escapeHtml(currentPost.writer)}</strong><span class="meta">${formatDate(currentPost.createdDate)}</span></div>
        <div class="detail-content">${escapeHtml(currentPost.content)}</div>
      </article>
      <section class="comments">
        <h3>댓글 <span class="count">${currentPost.commentList.length}</span></h3>
        <form id="comment-create-form" class="card comment-form" data-post-id="${postId}">
          <input name="writer" maxlength="50" placeholder="작성자" required><input name="content" maxlength="1000" placeholder="댓글을 입력하세요." required><button type="submit" class="btn">등록</button>
        </form>
        <div>${renderComments(currentPost.commentList)}</div>
      </section>`;
  } catch (error) { renderError(error); }
}

function renderComments(comments) {
  if (!comments.length) return '<div class="card empty">첫 댓글을 남겨보세요.</div>';
  return comments.map(comment => `
    <div class="comment" id="comment-${comment.id}">
      <div class="comment-top"><div><strong>${escapeHtml(comment.writer)}</strong> <span class="meta">${renderCommentTime(comment)}</span></div>
        <div class="actions"><button class="btn secondary small" onclick="showCommentEdit(${comment.id})">수정</button><button class="btn danger small" onclick="deleteComment(${comment.id})">삭제</button></div></div>
      <div class="comment-body">${escapeHtml(comment.content)}</div>
    </div>`).join('');
}

async function showPostForm(edit = false, updateHistory = true, postId = currentPost?.id) {
  if (edit && (!currentPost || currentPost.id !== Number(postId))) {
    app.innerHTML = '<div class="loading">게시글을 불러오는 중입니다.</div>';
    try { currentPost = await api(`/api/post/${postId}`); }
    catch (error) { return renderError(error); }
  }
  const post = edit ? currentPost : { title:'', writer:'', content:'' };
  if (updateHistory) updatePath(edit ? `/posts/${post.id}/edit` : '/posts/new');
  app.innerHTML = `
    <section class="toolbar"><div><h2>${edit ? '게시글 수정' : '새 글 작성'}</h2><span class="subtitle">필수 내용을 모두 입력해 주세요.</span></div></section>
    <form class="card form-card" onsubmit="savePost(event,${edit})">
      <div class="field"><label for="title">제목</label><input id="title" name="title" maxlength="200" value="${escapeHtml(post.title)}" required></div>
      <div class="field"><label for="writer">작성자</label><input id="writer" name="writer" maxlength="50" value="${escapeHtml(post.writer)}" ${edit ? 'readonly' : 'required'}></div>
      <div class="field"><label for="content">내용</label><textarea id="content" name="content" required>${escapeHtml(post.content)}</textarea></div>
      <div class="actions"><button type="button" class="btn secondary" onclick="${edit ? `showDetail(${post.id})` : 'showList()'}">취소</button><button class="btn">${edit ? '수정 완료' : '등록'}</button></div>
    </form>`;
}

async function savePost(event, edit) {
  event.preventDefault();
  const data = Object.fromEntries(new FormData(event.currentTarget));
  try {
    const saved = await api(edit ? `/api/post/${currentPost.id}` : '/api/post', { method:edit ? 'PUT' : 'POST', body:JSON.stringify(data) });
    showNotice(edit ? '게시글이 수정되었습니다.' : '게시글이 등록되었습니다.');
    await showDetail(saved.id);
  } catch (error) { showNotice(error.message); }
}

async function deletePost(postId) {
  if (!confirm('게시글과 댓글을 모두 삭제할까요?')) return;
  try { await api(`/api/post/${postId}`, { method:'DELETE' }); showNotice('게시글이 삭제되었습니다.'); await showList(); }
  catch (error) { showNotice(error.message); }
}

async function createComment(event, postId) {
  event.preventDefault();
  const form = event.target;
  const submitButton = form.querySelector('button[type="submit"]');
  const data = Object.fromEntries(new FormData(form));
  submitButton.disabled = true;
  try {
    await api(`/api/post/${postId}/comments`, { method:'POST', body:JSON.stringify(data) });
    showNotice('댓글이 등록되었습니다.');
    await showDetail(postId, false);
  } catch (error) {
    console.error('댓글 등록 실패:', error);
    showNotice(error.message);
    submitButton.disabled = false;
  }
}

function showCommentEdit(commentId) {
  const comment = currentPost.commentList.find(item => item.id === commentId);
  document.querySelector(`#comment-${commentId}`).innerHTML = `
    <form class="comment-form" onsubmit="updateComment(event,${commentId})">
      <input name="writer" maxlength="50" value="${escapeHtml(comment.writer)}" readonly><input name="content" maxlength="1000" value="${escapeHtml(comment.content)}" required>
      <div class="actions"><button type="button" class="btn secondary small" onclick="showDetail(${currentPost.id})">취소</button><button class="btn small">저장</button></div>
    </form>`;
}

async function updateComment(event, commentId) {
  event.preventDefault();
  const data = Object.fromEntries(new FormData(event.currentTarget));
  try { await api(`/api/post/${currentPost.id}/comments/${commentId}`, { method:'PUT', body:JSON.stringify(data) }); showNotice('댓글이 수정되었습니다.'); await showDetail(currentPost.id); }
  catch (error) { showNotice(error.message); }
}

async function deleteComment(commentId) {
  if (!confirm('댓글을 삭제할까요?')) return;
  try { await api(`/api/post/${currentPost.id}/comments/${commentId}`, { method:'DELETE' }); showNotice('댓글이 삭제되었습니다.'); await showDetail(currentPost.id); }
  catch (error) { showNotice(error.message); }
}

function renderError(error) {
  app.innerHTML = `<div class="card empty"><p>${escapeHtml(error.message)}</p><button class="btn" onclick="showList()">다시 시도</button></div>`;
}

function renderRoute() {
  const path = location.pathname.replace(/\/$/, '') || '/';
  if (path === '/') return showList(false);
  if (path === '/posts/new') return showPostForm(false, false);
  const editMatch = path.match(/^\/posts\/(\d+)\/edit$/);
  if (editMatch) return showPostForm(true, false, Number(editMatch[1]));
  const detailMatch = path.match(/^\/posts\/(\d+)$/);
  if (detailMatch) return showDetail(Number(detailMatch[1]), false);
  history.replaceState({}, '', '/');
  return showList(false);
}

addEventListener('popstate', renderRoute);
document.addEventListener('submit', event => {
  if (event.target.id !== 'comment-create-form') return;
  const postId = Number(event.target.dataset.postId);
  createComment(event, postId);
});
renderRoute();

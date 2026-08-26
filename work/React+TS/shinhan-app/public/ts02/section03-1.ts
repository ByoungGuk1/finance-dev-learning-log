// 꼭 다시 보기

class Repository<T> {
  private items: T[] = [];
  add(item: T) {
    this.items.push(item);
  }
  getAll(): T[] {
    return this.items;
  }
}

type ProductItem = {
  name: string;
  price: number;
};

const repo = new Repository<ProductItem>();
repo.add({ name: "keyboard", price: 30000 });
console.log(repo.getAll());

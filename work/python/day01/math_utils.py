import math

def squre(n):
  return math.sqrt(n*n)

def circle_area(r):
  return r*r*math.pi


# 이 모듈이 terminal에서 직접 실행 될 때 실행
if __name__ == "__main__":
  print(squre(5), circle_area(1))

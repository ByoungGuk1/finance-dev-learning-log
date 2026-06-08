package com.shinhan.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

public class Prob4 {

	public static void main(String[] args) {
		Product[] prodList = { new Product("NT450R5E-K24S", 500000, "삼성전자"),
				new Product("15UD340-LX2CK", 400000, "LG전자"), new Product("G2-K3T32AV", 600000, "HP") };
		HashSet<Product> product_hs = makeHashSet(prodList, 500000);
		makeFile(product_hs);
		readFile();

	}

	private static HashSet<Product> makeHashSet(Product[] prodList, int price) {
		HashSet<Product> result = new HashSet<>();
		Arrays.stream(prodList).filter((product) -> {
			return product.getPrice() >= price;
		}).forEach((data) -> {
			result.add(data);
		});
		System.out.println("*특정 금액 이상의 상품 결과입니다.*********");
		for (Product dd : result) {
			System.out.println(dd);
		}
		return result;
	}

	private static void makeFile(HashSet<Product> resultList) {
		try (FileOutputStream fos = new FileOutputStream("data.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			for (Product result : resultList) {
				oos.writeObject(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void readFile() {
		try (FileInputStream fis = new FileInputStream("data.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			HashSet<Product> productSet = new HashSet<Product>();
			Product readResult = null;
			try {
				while ((readResult = (Product) ois.readObject()) != null) {
					productSet.add(readResult);
				}
			} catch (java.io.EOFException e) {
			}
			System.out.println("*readFile결과입니다.*****************");
			for (Product result : productSet) {
				System.out.println(result);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

class Product implements Serializable {
	private String model_name;
	private int price;
	private String company;

	public Product(String model_name, int price, String company) {
		super();
		this.model_name = model_name;
		this.price = price;
		this.company = company;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Product [model_name=" + model_name + ", price=" + price + ", company=" + company + "]";
	}

}
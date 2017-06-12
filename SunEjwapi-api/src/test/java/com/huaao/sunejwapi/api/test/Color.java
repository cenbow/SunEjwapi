package com.huaao.sunejwapi.api.test;

public enum Color {
	RED("红色",1),GREEN("绿色",2),BLUE("蓝色",3);
	private String name;
	private int index;
	private Color(String name, int index) {
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public static String getName(int index){
		for(Color c:Color.values()){
			if(index == c.getIndex())
				return c.getName();
		}
		return null;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "(" + index + ":" +name +")";
	}
	
	public static void main(String args[]) {
		Color color = Color.GREEN;
		System.out.println(color.getIndex() + ":" + color.getName());
		
//		switch (color) {
//		case RED:
//			System.out.println("red");
//			break;
//		case BLUE:
//			System.out.println("blue");
//			break;
//		case GREEN:
//			System.out.println("green");
//			break;
//		}
		for(Color c : Color.values())
			System.out.println(c.getIndex()+":"+c.getName());
	}
	
}

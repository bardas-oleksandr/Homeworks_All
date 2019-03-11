import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
class Homework_02 {
	public static void main(String args[]) throws java.io.IOException {
		int choice;
		do{
			clearConsole();
			System.out.println("Menu");
			System.out.println("1 - Problem # 1");
			System.out.println("2 - Problem # 2");
			System.out.println("3 - Problem # 3");
			System.out.println("0 - Exit");
			System.out.print("Your choice:");
			do
			{
				choice = getInteger();
				if(choice < 0 || choice > 3) {
					System.out.print("Please, choose one of the suggested options. Try again:");
				}
			}while(choice < 0 || choice > 3);
			switch(choice)
			{
				case 1:
					System.out.println("Solving problem # 1");
					problem_1();
				break;
				case 2:
					System.out.println("Solving problem # 2");
					problem_2();
				break;
				case 3:
					System.out.println("Solving problem # 3");
					problem_3();
				break;
			}
		}while(choice != 0);
		System.out.print("Buy-buy!");
	}
	public final static int getInteger()	{
		Scanner in = new Scanner(System.in);
		while(true) {
			try {
				return in.nextInt();
			}
			catch (java.util.InputMismatchException e) {
				System.out.print("Incorrect input! Integer is expected! Try again:");
				String tmp = in.nextLine();	
			}
		}
		
	}
	public final static void clearConsole() {
		try {
        		if (System.getProperty("os.name").contains("Windows"))
            			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        		else
            			Runtime.getRuntime().exec("clear");
    		}
		catch (IOException | InterruptedException ex) {}
	}
	public final static void problem_1() {
		System.out.print("Set dimensions count (3..5):");
		int count;
		do	//������ ����� ��������� �������
		{
			count = getInteger();
			if(count < 3 || count > 5) {
				System.out.print("Set the number between 3 and 5:");
			}
		}while(count < 3 || count > 5);
		int size[] = getArray(count);	//���� ������� ������������
		switch(count) {
			case 3:
			{
				int mas[][][] = new int[size[0]][size[1]][size[2]];
				int result = 0;
				int number = 0;
				//�������������, ����������� � ������� ����� ��������� ����������� �������
				for(int[][] x: mas) {
					number++;
					System.out.println("Array " + number);	//���������� ������ - ��� ������ ���������� ��������. ��� � ����� �� ����������
					for(int[] y: x) {
						for(int z: y) {
							Random rnd = new Random();
							z = rnd.nextInt(10);
							result += z;
							System.out.print(z + "\t");
						}
						System.out.print("\n");
					}
				}
				System.out.println("Sum of elements is equal " + result);
			}
			break;
			case 4:
			{
				int mas[][][][] = new int[size[0]][size[1]][size[2]][size[3]];
				int result = 0;
				int number_1 = 0;
				//�������������, ����������� � ������� ����� ��������� �������������� �������
				for(int[][][] a: mas) {
					number_1++;
					int number_2 = 0;
					for(int[][] x: a) {
						number_2++;
						System.out.println("Array " + number_1 + "-" + number_2);	//������������� ������ ����� ���������� � ���� ������ ���������� ��������
														//��� ������� �� ���������� �������� ��������� �� ������� (��������� 3 � 4)
						for(int[] y: x) {
							for(int z: y) {
								Random rnd = new Random();
								z = rnd.nextInt(10);
								result += z;
								System.out.print(z + "\t");
							}
							System.out.print("\n");
						}
					}
				}
				System.out.println("Sum of elements is equal " + result);
			}	
			break;
			case 5:
			{
				int mas[][][][][] = new int[size[0]][size[1]][size[2]][size[3]][size[4]];
				int result = 0;
				int number_1 = 0;
				//�������������, ����������� � ������� ����� ��������� ����������� �������
				for(int[][][][] b: mas) {
					number_1++;
					int number_2 = 0;
					for(int[][][] a: b) {
						number_2++;
						int number_3 = 0;
						for(int[][] x: a) {
							number_3++;
							System.out.println("Array " + number_1 + "-" + number_2 + "-" + number_3);	//���������� ������ ����� ���������� � ���� ������ ���������� ��������
																	//��� ������� �� ���������� �������� ��������� �� ������� (��������� 3, 4 � 5)
							for(int[] y: x) {
								for(int z: y) {
									Random rnd = new Random();
									z = rnd.nextInt(10);
									result += z;
									System.out.print(z + "\t");
								}
								System.out.print("\n");
							}
						}
					}
				}
				System.out.println("Sum of elements is equal " + result);
			}	
			break;
		}
		System.out.print("Press enter");
		Scanner in = new Scanner(System.in);
		String tmp = in.nextLine();
	}
	public final static void problem_2() {
		final int maxSize = 5;
		Stack myStack = new Stack(maxSize);	//���� �� 5 ���������
		for(int i = 0; i < maxSize + 1; i++) {	//��������� �������� �� ������������ �����
			if(myStack.push(i)) {
				try {
					System.out.println("Value " + myStack.top() + " was added");
				}
				catch (StackIsEmpty e) {
					System.out.println("You will never see this");
				}
			}
			else {
				System.out.println("Stack overflow");	
			}	
		}
		for(int i = 0; i < maxSize + 1; i++) {	//������� �������� �� �����
			try {
				int x = myStack.pop();
				System.out.println("Value " + x + " was removed from stack");
			}
			catch (StackIsEmpty e) {
				System.out.println("Stack is empty");
			}
		}
		System.out.print("Press enter");
		Scanner in = new Scanner(System.in);
		String tmp = in.nextLine();
	}
	public final static void problem_3() {
		final int maxSize = 5;
		Queue myQueue = new Queue(maxSize);	//������� �� 5 ���������
		for(int i = 0; i < maxSize + 1; i++) {	//��������� �������� �� ������������ �������
			if(myQueue.enque(i)) {
				System.out.println("Value " + i + " was added");
				myQueue.show();
			}
			else {
				System.out.println("Queue overflow");	
			}	
		}
		for(int i = 0; i < maxSize + 1; i++) {	//������� �������� �� �������
			try {
				int x = myQueue.deque();
				System.out.println("Value " + x + " was removed from queue");
				myQueue.show();
			}
			catch (QueueIsEmpty e) {
				System.out.println("Queue is empty");
			}
		}
		System.out.print("Press enter");
		Scanner in = new Scanner(System.in);
		String tmp = in.nextLine();
	}
	public final static int[] getArray(int count) {
		int size[] = new int[count];
		for(int i = 0; i < count; i++) {
			do {
				System.out.print("Set size of " + (i+1) + " dimension:");
				size[i] = getInteger();
				if(size[i] <= 0) {
					System.out.print("Incorrect input! Positive integer expected:");
				}
			} while(size[i] <= 0);
		}
		return size;
	}
}

class Stack {
	private int maxSize;
	private int stack[];
	private int tos;
	public Stack(int _maxSize) {
		maxSize = _maxSize;
		stack = new int[maxSize];
		tos = -1;
	}
	public boolean push(int value) {
		if(tos + 1 == maxSize) {
			return false;	//StackOverflow
		}
		tos++;
		stack[tos] = value;
		return true;
	}
	public int pop() throws StackIsEmpty {
		if(tos == -1) {
			throw new StackIsEmpty();	//StackIsEmpty. � ���������� ��������� ������ �� ������ �������� ����� ����������:
							//1-���������� �� ������ �������� �� �����. 2-���� ��������.
							//��� ��� � Java � ����� ������ �������� �������� �� ������ (� ����� �������� �� ������ ���������� ��������),
							//�� ��������� ���������� � ������ �������� �������� ����� ������������ �������
		}
		tos--;
		return stack[tos + 1];
	}
	public int top() throws StackIsEmpty  {
		if(tos < 0) {
			throw new StackIsEmpty();
		}
		return stack[tos];
	}
}

class StackIsEmpty extends Exception {}

class Queue {
	int capacity;	//������������ ����� �������
	int last;	//����� ��������, ������������ ��������� � �������
	int queue[];
	public Queue(int _capacity) {
		capacity = _capacity;
		last = -1;
		queue = new int[capacity];
	}
//���������� � �������
	public boolean enque(int value) {
		if(last + 1 == capacity) {
			return false;	//Queue overflow
		}
		last++;
		queue[last] = value;
		return true;
	}
//������ �� �������
	public int deque()  throws QueueIsEmpty {
		if(last == -1) {
			throw new QueueIsEmpty();
		}
		int value = queue[0];
		for(int i = 0; i < last; i++) {
			queue[i] = queue[i+1];
		}
		last--;
		return value;
	}
	public void show() {
		for(int i = last; i >= 0; i--) {
			System.out.print(queue[i] + "\t");
		}
		System.out.print("\n");
	}
}

class QueueIsEmpty extends Exception {}




















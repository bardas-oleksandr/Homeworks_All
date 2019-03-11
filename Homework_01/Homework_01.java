//�������� ������� � ����� �1
//�������� ��. ������ 17-5
//������� ���������

import java.util.Scanner;
import java.util.Random;
class Homework_01
{
	public static void main(String args[])
	{
//������ 1. ����� ������� m*n
		int answer=0;
		Random rnd = new Random();
		System.out.print("Set rows count:");
		int m = getInteger();	//����� getInteger() ������ ����. ����� ��������� ����� �� ��� ��� ���� �� ������� ����� �����
					//������ �������� �������������� ��� ����������, � ���������� ���� ��������� ��������� ����.
		System.out.print("Set columns count:");
		int n = getInteger();
		int[][] mas = new int[m][n];
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				mas[i][j] = rnd.nextInt(100);
				System.out.print(mas[i][j] + "\t");
				answer += mas[i][j];
			}
			System.out.print("\n");
		}
		System.out.println("The answer is " + answer);
//������ 2. ��������� �����
		System.out.print("Your value:");
		int x = getInteger();
		System.out.println(x + "!=" + Fact(x));
//������ 3. ��������� ����� �� �������
		System.out.print("Set rows count:");
		m = getInteger();
		System.out.print("Set columns count:");
		n = getInteger();
		int[][] matrix = new int[m][n];
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				matrix[i][j]=rnd.nextInt(2);
				System.out.print(matrix[i][j]);
			}
			System.out.print("\n");
		}
		double koef = (double)m / n;	//���������� ������� ��������� ��������� �������� ����� ��� ������������ ���������������
		//1 �����
		System.out.println("First mask");
		for(int i=0; i<m; i++)
		{
			for(int j=0; j < n; j++)
			{
				if(i + j * koef + 1 < m)
					System.out.print(matrix[i][j]);
				else
					System.out.print(" ");
			}
			System.out.print("\n");
		}
		//2 �����
		System.out.println("Second mask");
		for(int i=0; i<m; i++)
		{
			for(int j=0; j < n; j++)
			{
				if(i + j * koef + 1 >= m)
					System.out.print(matrix[i][j]);
				else
					System.out.print(" ");
			}
			System.out.print("\n");
		}
		//3 �����
		System.out.println("Third mask");
		for(int i=0; i<m; i++)
		{
			for(int j=0; j < n; j++)
			{
				if(i + j * koef + 1 < m && i < j *koef || i + j * koef + 1 >= m && i >= j *koef)
					System.out.print(matrix[i][j]);
				else
					System.out.print(" ");
			}
			System.out.print("\n");
		}
		//4 �����
		System.out.println("Fourth mask");
		for(int i=0; i<m; i++)
		{
			for(int j=0; j < n; j++)
			{
				if(i + j * koef + 1 < m && i >= j *koef  || i + j * koef + 1 >= m && i < j *koef)
					System.out.print(matrix[i][j]);
				else
					System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	public static int getInteger()	//����� ��� ��������� ������ �����. ���� �������� ���-�� ������ - ����������� ��������� ���������� � ������ �� ���� �����������.
	{
		Scanner in = new Scanner(System.in);
		boolean incorrect = true;
		int x = 0;
		while(incorrect)
		{
			try
			{
				x = in.nextInt();
				incorrect = false;
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Incorrect input. Try again:");
				String mes = in.nextLine();	//���� "������" ���������� ����. ����� � ���� ���������� ����������� ���� ����������� �������
			}
		}
		return x;
	}
	public static int Fact(int x)
	{
		int answer = 1;
		for(int i=2; i <= x; i++)
			answer *= i;
		return answer;
	}
}
//Домашнее задание к уроку №1
//Выполнил ст. группы 17-5
//Бардась Александр

import java.util.Scanner;
import java.util.Random;
class Homework_01
{
	public static void main(String args[])
	{
//Задача 1. Вывод матрицы m*n
		int answer=0;
		Random rnd = new Random();
		System.out.print("Set rows count:");
		int m = getInteger();	//Метод getInteger() описан ниже. Будет требовать ввода до тех пор пока не получит целое число
					//Прочие значения обрабатываются как исключения, в результате чего требуется повторный ввод.
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
//Задача 2. Факториал числа
		System.out.print("Your value:");
		int x = getInteger();
		System.out.println(x + "!=" + Fact(x));
//Задача 3. Наложение маски на матрицу
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
		double koef = (double)m / n;	//Коэфициент который позволяет правильно выводить маску для неквадратных прямоугольников
		//1 маска
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
		//2 маска
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
		//3 маска
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
		//4 маска
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
	public static int getInteger()	//Метод для получения целого числа. Если получено что-то другое - выполняется обработка исключения и запрос на ввод повторяется.
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
				String mes = in.nextLine();	//Надо "съесть" предыдущий ввод. Иначе у меня получается бесконечный цикл обработчика событий
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
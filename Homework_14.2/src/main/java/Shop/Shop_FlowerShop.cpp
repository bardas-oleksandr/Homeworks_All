#include<jni.h>
#include<iostream>
#include"Shop_FlowerShop.h"
#include<iomanip>	//setprecision
#include<sstream>	//stringstream

using namespace std;

JNIEXPORT jcharArray JNICALL Java_Shop_FlowerShop_printCheck(JNIEnv * env, jobject obj, jobjectArray flowers)
{
	double total = 0;	//Общая стоимость по чеку
	jobject flower;	//Цветок из букета
	int size = env->GetArrayLength(flowers);	//Размер букета
	string check = "CHECK\n";	//Тут собираем весь текст чека
	for (int i = 0; i < size; i++) {
		flower = env->GetObjectArrayElement(flowers, i);	//Получаем отдельный цветок через интерфейс к виртуальной машине env
		jclass flowerClass = env->GetObjectClass(flower);	//Получаем класс цветка
		jfieldID fieldPriceID = env->GetFieldID(flowerClass, "price", "D");	//Получаем ID поля double price (D - означает double) из класса flowerClass
		double price = env->GetDoubleField(flower,fieldPriceID);	//Получаем значение типа double из объекта flower
		total += price;
		jfieldID fieldColorID = env->GetFieldID(flowerClass, "color", "Ljava/lang/String;");	//Получаем ID поля с цветом цветка
																								//Ljava/lang/String; - означает тип String. Все аргументы типа Object должны начинаться символом "L" и знаканчиваться символом ";" (см. D:\Programming\Java\02_Homeworks\JNI\Accessing Java Fields)
																								//Массивы начинаются с символа "["
		jstring colorString = (jstring)env->GetObjectField(flower, fieldColorID);	//Получаем значение поля с цветом
		const char* color = env->GetStringUTFChars(colorString, 0);
		check.append("color: ");
		check.append(color);
		env->ReleaseStringUTFChars(colorString, color);	//Это сигнал для JVM что значения массива больше не нужны. Что-то типа разблокировки
		check.append("\tprice: ");
		stringstream stream;
		stream << fixed << setprecision(2) << price;
		check.append(stream.str());
		stream.clear();
		check.append("$\n");
	}
	check.append("Total price: ");
	stringstream stream;
	stream << fixed << setprecision(2) << total;
	check.append(stream.str());
	stream.clear();
	check.append("$");
	jcharArray result = env->NewCharArray(check.length());
	jchar* tmp = (jchar*)calloc(sizeof(jchar), check.length());
	for (int i = 0; i < check.length(); i++)
		tmp[i] = check[i];
	env->SetCharArrayRegion(result, 0, check.length(), tmp);
	free(tmp);
	return result;
}

JNIEXPORT jdouble JNICALL Java_Shop_FlowerShop_calculate(JNIEnv * env, jobject obj, jobjectArray flowers)
{
	double total = 0;
	jobject flower;
	int size = env->GetArrayLength(flowers);
	for (int i = 0; i < size; i++) {
		flower = env->GetObjectArrayElement(flowers, i);	//Получаем отдельный цветок через интерфейс к виртуальной машине env
		jclass flowerClass = env->GetObjectClass(flower);	//Получаем класс цветка
		jfieldID fieldPriceID = env->GetFieldID(flowerClass, "price", "D");	//Получаем ID поля double price (D - означает double) из класса flowerClass
		jdouble price = env->GetDoubleField(flower, fieldPriceID);	//Получаем значение типа double из объекта flower
		total += price;
	}
	int temp = (total * 100);	//Чтобы не видеть числа типа 2,9999999999999999999
	total = temp / 100.0;
	return total;
}

JNIEXPORT jint JNICALL Java_Shop_FlowerShop_summ(JNIEnv * env, jobject object, jintArray arr) {
	int res = 0;
	int size = env->GetArrayLength(arr);
	jint* src = env->GetIntArrayElements(arr, NULL);
	for (int i = 0; i < size; i++) {
		res += src[i];
	}
	return res;
}
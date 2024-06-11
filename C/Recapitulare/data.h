#include <stdio.h>

typedef struct Item
{
int code; 
float price;
char* name;
}Item;


Item newItem(int code,const char* name,float price);
void deleteItem(Item *item);
Item newItemFromFile(FILE* file);
void printItem(Item item);
#include "data.h"
#include <malloc.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

Item newItem(int code,const char* name,float price)
{
    Item item;
    item.code = code;
    item.price= price;
    item.name = (char*)malloc((strlen(name)+1)*sizeof(char));
    strcpy(item.name,name);
    return item;
};

void deleteItem(Item *item)
{
    free(item->name);
    item->name = NULL;
}

Item newItemFromFile(FILE* file)
{
    Item item;
    item.code = 0;
    item.name = NULL;
    item.price = 0.0f;

    if(!feof(file))
    {
        char buffer[50];
        fscanf(file,"%d",&item.code);
        fscanf(file, "%f",&item.price);
        fscanf(file,"%s",buffer);

        item.name = (char*)malloc(strlen(buffer)+1);
        strcpy(item.name,buffer);
    }

    
    return item;
}

void printItem(Item item)
{
    printf("Id:%d Nume:%s Pret:%.2f\n",item.code,item.name,item.price);
}
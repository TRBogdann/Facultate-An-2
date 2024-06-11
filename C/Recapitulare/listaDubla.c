#include "data.h"
#include <stdio.h>
#include <malloc.h>
#include <string.h>

typedef struct Node{
Item data;
struct Node* next;
struct Node* prev;
}Node;

typedef struct List
{
Node* first;
Node* last;
}List;

List newList()
{
    return (List){NULL,NULL};
};
void insertFirst(List *list,Item item)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data.code = item.code;
    newNode->data.price = item.price;
    newNode->data.name = (char*)malloc(strlen(item.name)+1);
    strcpy(newNode->data.name,item.name);
    if((*list).first!=NULL)
        (*list).first->prev = newNode;
    else 
        (*list).last = newNode;
    newNode->next = (*list).first;
    newNode->prev = NULL;
    (*list).first = newNode;
}

void insertLast (List *list,Item item)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data.code = item.code;
    newNode->data.price = item.price;
    newNode->data.name = (char*)malloc(strlen(item.name)+1);
    strcpy(newNode->data.name,item.name);
    if((*list).last!=NULL)
        (*list).last->next = newNode;
    else 
        (*list).first = newNode;
    newNode->prev = (*list).last;
    newNode->next = NULL;
    (*list).last = newNode;
}

void printList(List list)
{
    while(list.first!=NULL)
    {
        printItem(list.first->data);
        list.first = list.first->next;
    }
}

void printListRev(List list)
{
    while(list.last!=NULL)
    {
        printItem(list.last->data);
        list.last = list.last->prev;
    }
}

List genList(const char* filename)
{
    FILE* file = fopen(filename,"rt");
    List list = newList();
    while (!feof(file)) {
        insertLast(&list,newItemFromFile(file));
    }
    fclose(file);
    return list;
}

void deleteList(List* list)
{   
    Node *begin = list->first;
    Node *end = NULL;
    while(begin != NULL)
    {
        end = begin->next;
        free(begin);
        begin = end;
    }
    list->first = NULL;
    list->last = NULL;
}
int main()
{
    List list = genList("data.txt");
    /*list = newList();
    insertLast(&list,newItem(2,"Paine",2.7));
    insertLast(&list,newItem(1,"Paine Integrala",2.5));
    */
    printList(list);
    deleteList(&list);
    return 0;
}
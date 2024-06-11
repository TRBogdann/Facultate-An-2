#include <stdio.h>
#include <malloc.h>
#include <string.h>


typedef struct{
    char* producator;
    int serie;
}Masina;

Masina newMasina(const char* producator,const int serie)
{
    Masina newMasina;

    newMasina.producator = (char*)malloc((strlen(producator)+1)*sizeof(char));
    strcpy(newMasina.producator,producator);

    newMasina.serie=serie;

    return newMasina;
}

void afisare(Masina m)
{
    printf("Masina %s cu seria %d\n",m.producator,m.serie);
}

typedef struct Node{
    struct Node* next;
    struct Node* prev;
    Masina data;
}Node;

typedef struct List{
    Node* first;
    Node* last;
}List;

List newList()
{
    List list;
    list.first = NULL;
    list.last = NULL;
    return list;
}

void insertNewFirst(List* list, Masina m)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    
    newNode->data.serie=m.serie;
    newNode->data.producator = (char*)malloc((strlen(m.producator)+1)*sizeof(char));
    strcpy(newNode->data.producator,m.producator);
    newNode->prev = NULL;
    newNode->next = (*list).first;

    
    if((*list).first != NULL)
        (*list).first ->prev = newNode;
    (*list).first = newNode;
    
    if((*list).first->next==NULL)
        (*list).last=(*list).first;
}

void insertNewLast(List *list,Masina m)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    
    newNode->data.serie=m.serie;
    newNode->data.producator = (char*)malloc((strlen(m.producator)+1)*sizeof(char));
    strcpy(newNode->data.producator,m.producator);

    newNode->next = NULL;
    newNode->prev = (*list).last;
    if((*list).last != NULL)
        (*list).last ->next = newNode;
    (*list).last = newNode;

    if((*list).last->prev==NULL)
        (*list).first=(*list).last;

}

void printList(List list)
{
    while(list.first)
    {
        afisare(list.first->data);
        list.first = list.first->next;
    }
}

void printListEnd(List list)
{
    while(list.last)
    {
        afisare(list.last->data);
        list.last = list.last->prev;
    }
}




void deleteMasina(Masina m)
{
    free(m.producator);
}

void deleteList(List *list)
{
    if(list->first == NULL)
        return;

    Node *copy = (*list).first;
    
    while(copy)
    {
        if(copy->prev){
            free(copy->prev);
            deleteMasina(copy->prev->data);
        }
         copy = copy->next;
    }

    if(list->last)
        free(list->last);

    (*list).first=NULL;
    (*list).last=NULL;
}

int nrNoduri(List list)
{
    int count = 0;
    while(list.first)
    {
        count ++;
        list.first = list.first->next;
    }
    return count;
}

Masina* listToVec(List list,int *len)
{
    *len = nrNoduri(list);
    Masina* vec = (Masina*)malloc(sizeof(Masina)*(*len));
    int i = 0;
    while(list.first)
    {
        vec[i] = newMasina(list.first->data.producator,list.first->data.serie);
        list.first = list.first->next;
        i++;
    }

    return vec;

};

void printCircularList(List list)
{
    if(list.first == NULL)
        return;
    Node * temp = list.first;
    do
    {
        afisare(temp->data);
        temp = temp->next;
    }
    while(temp!=list.first);
}

int main()
{
List list = newList();

insertNewFirst(&list,newMasina("Ford",1) );
insertNewLast(&list,newMasina("Renault",2) );
insertNewFirst(&list,newMasina("Mercedes",3) );

int len = 0;
Masina* vec = listToVec(list, &len);
printList(list);

for(int i=0;i<len;i++)
{
    afisare(vec[i]);
}

for(int i=0;i<len;i++)
{
    deleteMasina(vec[i]);
}

//list.last->next=list.first;
//list.first->prev=list.last;
//printCircularList(list);

free(vec);
deleteList(&list);
    return 0;
}
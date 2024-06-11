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
    Masina data;
}Node;

void insertNewFirst(Node** first, Masina m)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    
    newNode->data.serie=m.serie;
    newNode->data.producator = (char*)malloc((strlen(m.producator)+1)*sizeof(char));
    strcpy(newNode->data.producator,m.producator);
    newNode->next = *first;

    *first = newNode;
}

void insertLast(Node **first,Masina m)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    
    newNode->data.serie=m.serie;
    newNode->data.producator = (char*)malloc((strlen(m.producator)+1)*sizeof(char));
    strcpy(newNode->data.producator,m.producator);
    newNode->next = NULL;

    if(*first==NULL)
    {
        *first = newNode;
        return;
    }
    Node* temp = *first;
    while (temp->next != NULL) {
        temp = temp->next;
    }
    temp->next = newNode;

}


void deleteMasina(Masina m)
{
    free(m.producator);
}

void deleteList(Node** first)
{
    if(*first == NULL)
        return;
    Node* nextNode = (*first)->next;
    deleteMasina((*first)->data);
    free((*first));
    
    while(nextNode != NULL)
    {
        *first = nextNode;
        nextNode = nextNode->next;
        deleteMasina((*first)->data);
        if(first)free(*first);
    }

    first = NULL;
    
}

void afisareLista(Node* first)
{
    while(first)
    {
        afisare(first->data);
        first = first->next;
    }
}

int nrOf(Node* first,const char* masina)
{
    int count = 0;
    while(first != NULL)
    {
        if(!strcmp(masina,first->data.producator))
            count++;
        first = first->next;
    }
    return count;
}

void pop(Node* first)
{
    Node *aux = first;
    if(first == NULL) return;
    if(first->next == NULL)
    {
        deleteMasina(first->data);
        free(first);
        first = NULL;
        return;
    }
    while(aux->next->next != NULL)
    {
        aux = aux->next;
    }
    deleteMasina(aux->next->data);
    free(aux->next);
    aux->next=NULL;
}

int main()
{
    Node *first = NULL;
    Masina m = newMasina("Ford", 1);
    Masina m1 = newMasina("Toyota", 2);
    Masina m2= newMasina("Honda", 3);
    Masina m3 = newMasina("Volvo", 4);

    insertNewFirst(&first,m);
    insertNewFirst(&first,m1);
    insertNewFirst(&first,m2);
    insertNewFirst(&first,m3);
    insertLast(&first, m3);

    afisareLista(first);
    printf("\n");
    pop(first);
    afisareLista(first);


    return 0;
}
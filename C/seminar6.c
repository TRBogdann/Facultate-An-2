#include <stdio.h>
#include <malloc.h>
#include <string.h>


typedef struct{
    long id;
    char* producator;
    int serie;
}Masina;

Masina newMasina(long id,const char* producator,const int serie)
{
    Masina newMasina;

    newMasina.producator = (char*)malloc((strlen(producator)+1)*sizeof(char));
    strcpy(newMasina.producator,producator);

    newMasina.serie=serie;

    newMasina.id=id;

    return newMasina;
}

void afisare(Masina m)
{
    printf("%ld: Masina %s cu seria %d\n",m.id,m.producator,m.serie);
}

typedef struct Node{
    struct Node* next;
    Masina data;
}Node;


void push(Node** first, Masina m)
{
 Node* newNode = (Node*)malloc(sizeof(Node));
    
    newNode->data.id = m.id;
    newNode->data.serie=m.serie;
    newNode->data.producator = (char*)malloc((strlen(m.producator)+1)*sizeof(char));
    strcpy(newNode->data.producator,m.producator);
    newNode->next = *first;

    *first = newNode;
}


void put(Node **first,Masina m)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    
    newNode->data.id = m.id;
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


void deleteLast(Node* first)
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

Masina pop(Node **top )
{
    if(top == NULL)
    {
        return newMasina(0,"", 0);
    }

    Masina m1 = newMasina((*top)->data.id,(*top)->data.producator,(*top)->data.serie);
    Node* temp = *top;
     *top = (*top)->next; 
    deleteMasina(temp->data);
    free(temp);
    return m1;
};

char isEmpty(Node* top)
{
    return top == NULL;
}

Masina searchStack(long id,Node** s)
{
    Node* temp = NULL;
    Masina m1;
    while((*s)->data.id!=id && !isEmpty(*s))
    {
        push(&temp,pop(&(*s)));
    }
    if(s == NULL)
        m1 =  newMasina(0,"", 0);
    else 
    {
        m1 = pop(&(*s));
    }
    while(!isEmpty(temp))
    {
        push(&(*s),pop(&temp));
    }
    return m1;

}

int main()
{
    Node *stack= NULL;
    Node *queue = NULL;
    Masina m = newMasina(1,"Ford", 1);
    Masina m1 = newMasina(2,"Toyota", 2);
    Masina m2= newMasina(3,"Honda", 3);
    Masina m3 = newMasina(4,"Volvo", 4);

    push(&stack,m);
    push(&stack,m1);
    push(&stack,m2);
    push(&stack,m3);

    put(&queue,m);
    put(&queue,m1);
    put(&queue,m2);
    put(&queue,m3);


    Masina found =searchStack(2, &stack);
    afisare(found);

    while(!isEmpty(stack))
    {
        afisare(pop(&stack));
    }
    
    printf("\n");

        while(!isEmpty(queue))
    {
        afisare(pop(&queue));
    }
    
   
   // afisareLista(first);


    return 0;
}
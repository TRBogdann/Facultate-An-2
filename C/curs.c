#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct node
{
char format;
void* data;
struct node *next;
int size;
}dynamicNode;

dynamicNode *newList()
{
    dynamicNode *p=(dynamicNode*)malloc(sizeof(dynamicNode));
    p->next=NULL;
    p->data=0;
    p->format=0;
    p->size=0;
    return p ;
}


void appendElement(dynamicNode *p,void* element,char format,int size)
{
    dynamicNode *temp=p;
    while(temp->next!=NULL)
        temp=temp->next;
    
    dynamicNode *newElement = (dynamicNode*)malloc(sizeof(dynamicNode));
    temp->next=newElement;
    newElement->next=NULL;

    newElement->size=1;

    switch(format)
    {   
        case 'd':{
            int a=*(int*)element;
            int *e = (int*)malloc(sizeof(int));
            *e=a;
            newElement->data=(void*)e;
            newElement->format='d';
            break;
        }
        case 'c':{
            char a=*(char*)element;
            char *e = (char*)malloc(sizeof(char));
            *e=a;
            newElement->data=(void*)e;
            newElement->format='c';
            break;
        }
        case 'f':{
            float a=*(float*)element;
            float *e = (float*)malloc(sizeof(float));
            *e=a;
            newElement->data=(void*)e;
            newElement->format='f';
            break;
        }

        case 's':{
            char* s=(char*)malloc(sizeof(char)*size+1);
            strcpy(s,(char*)element);
            newElement->data=(void*)s;
            newElement->format='s';
            newElement->size=size;
            break;
        }

        case 'a':
        {
            int *a=(int*)malloc(sizeof(int)*size);
            for(int i=0;i<size;i++)
            {
                a[i]=((int*)element)[i];
            }
            newElement->data=(void*)a;
            newElement->format='a';
            newElement->size=size;
            break;
        }

        default:
        {
            newElement->data=NULL;
        break;
        }
    }
}

void deleteList(dynamicNode* p)
{
    dynamicNode *n=p->next;

    while(n!=NULL)
    {
        dynamicNode *temp=n;
        n=n->next;
        if(temp->data)free(temp->data);
        if(temp)free(temp);
    }

}

void printList(dynamicNode* p)
{
    while(p!=NULL)
    {
            switch(p->format)
    {   
        case 'd':{
            int temp=*(int*)p->data;
            printf("%d ",temp);
            break;
        }
        case 'c':{
            char temp=*(char*)p->data;
            printf("%c" ,temp);
            break;
        }
        case 'f':{
            float temp=*(float*)p->data;
            printf("%0.2f ",temp);
            break;
        }

        case 's':{
            printf("%s ",(char*)p->data);
            break;
        }

        case 'a':
        {
            printf("[");
            for(int i=0;i<p->size;i++)
            {
                printf("%d",((int*)p->data)[i]);
                if(i<p->size-1)
                    printf(",");
            }
            printf("] ");
            break;
        }

        default:
        {
        break;
        }
    }
    p=p->next;
    }
}

int main()
{
    dynamicNode *p=newList();
    int* a=(int*)malloc(sizeof(int));
    float b=2.0f;
    *a=1;

    p->data=a;
    p->format='d';
    p->size=1;



    appendElement(p,(void*)"mystr",'s',strlen("mystr"));
    appendElement(p, (int[]){1,2,3,8,9}, 'a',5);
    appendElement(p, &b,'f',1);

    printList(p);

    deleteList(p);

    return 0;
}
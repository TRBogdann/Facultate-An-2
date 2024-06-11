#include <stdio.h>
#include <string.h>
#include <malloc.h>


typedef struct Prajitura
{
    int id;
    char *denumire;
        float pret;
}Prajitura;

void printPrajitura(Prajitura p)
{
    printf("Id: %d Nume: %s Pret: %.2f\n",p.id,p.denumire,p.pret);
}

Prajitura newPrajitura(int id, const char* denumire, float pret)
{
    Prajitura p;
    p.denumire = (char*)malloc(strlen(denumire)+1);
    strcpy(p.denumire,denumire);
    p.pret = pret;
    p.id = id;

    return p;
}

typedef struct Node
{
    Prajitura data;
    struct Node* left;
    struct Node* right;
}Node;

int treeHeight(Node* node)
{
    if(node == NULL)
        return 0;
    int sum = 1;
    int a = treeHeight(node->left);
    int b = treeHeight(node->right);
    if(a>b)
        sum += a;
    else 
        sum += b;
    return sum;
}

int diffHeight(Node* node)
{
    if(node == NULL)
        return 0;
    return treeHeight(node->left)-treeHeight(node->right);
}

void concatTree(Node** node,Node** node2)
{
    if(*node == NULL)
    {
        *node = *node2;
        return;
    }

    if((*node2)->data.id>(*node)->data.id)
    {
        if((*node)->right == NULL)
            (*node)->right = (*node2);
        else
            concatTree(&(*node)->right, node2);
        return;
    }
     if((*node2)->data.id<(*node)->data.id)
    {
        if((*node)->left == NULL)
            (*node)->left = (*node2);
        else
            concatTree(&(*node)->left, node2);
        return;
    }
}

void leftBalance(Node** node)
{
        Node *newRoot = (*node)->right;
        Node* broken = newRoot->left;
        (*node)->right = NULL;
        newRoot->left = *node;
        (*node) = newRoot;
        concatTree(node,&broken);
}

void rightBalance(Node** node)
{
        Node *newRoot = (*node)->left;
        Node* broken = newRoot->right;
        (*node)->left = NULL;
        newRoot->right = *node;
        (*node) = newRoot;
        concatTree(node,&broken);
}


void balace(Node** node)
{
    Node* broken;
    int dif  = diffHeight(*node);
    if(dif <= 1 && dif >= -1)
        return;
    
    if(dif<-1)
    {
        //rebalansare subarbore
        leftBalance(node);    
    }
    
    if(dif>1)
    {
        //rebalansare subarbore
        rightBalance(node);
    }

    
    
}

void InsertNode(Node** node,Prajitura data)
{
    if(*node ==  NULL)
    {
        *node = (Node*)malloc(sizeof(Node));
        (*node)->data = newPrajitura(data.id,data.denumire,data.pret);
        (*node)->left = NULL;
        (*node)->right = NULL;
        return;
    }
    if((*node)->data.id>data.id)
    {
        if((*node)->left==NULL)
        {
            Node* newNode = (Node*)malloc(sizeof(Node));
            newNode->data = newPrajitura(data.id,data.denumire,data.pret);
            newNode->left = NULL;
            newNode->right = NULL;
            (*node)->left = newNode;
        }
        else 
            InsertNode(&((*node)->left),data);
        return;
    }
    if((*node)->right==NULL)
        {
            Node* newNode = (Node*)malloc(sizeof(Node));
            newNode->data = newPrajitura(data.id,data.denumire,data.pret);
            newNode->left = NULL;
            newNode->right = NULL;
            (*node)->right = newNode;
        }
    else 
        InsertNode(&((*node)->right),data);

    balace(node);

}

void printTreeSRD(Node* node)
{
    if(node == NULL)
        return;
    printTreeSRD(node->left);
    printPrajitura(node->data);
    printTreeSRD(node->right);
}

void printTreeRSD(Node* node)
{
    if(node == NULL)
        return;
    printPrajitura(node->data);
    printTreeRSD(node->left);
    printTreeRSD(node->right);
}

void printTreeSDR(Node* node)
{
    if(node == NULL)
        return;
    printTreeSDR(node->left);
    printTreeSDR(node->right);
    printPrajitura(node->data);
}

void deleteTree(Node** node)
{
    if((*node) == NULL)
        return;
    deleteTree(&((*node)->left));
    deleteTree(&((*node)->right));

    free((*node)->data.denumire);
    free(*node);

    (*node) = NULL;
}

Prajitura search(Node* node,int id)
{
    if(node==NULL)
        return newPrajitura(-1, "",0.0f);

    if(node->data.id==id)
        return newPrajitura(node->data.id,node->data.denumire, node->data.pret);

    if(node->data.id < id)
        return search(node->right,id);

    return search(node->left,id);
};


void printParents(Node* node,int parent_id)
{
    if(node==NULL)
        return;
    printf("Parent of %d is %d\n",node->data.id,parent_id);
    printParents(node->left, node->data.id);
    printParents(node->right, node->data.id);
}

int main()
{
    Node* tree = NULL;
    
    InsertNode(&tree, newPrajitura(5,"Amandina",2.7f));
    InsertNode(&tree, newPrajitura(2,"LavaCake",10.5f));
    InsertNode(&tree, newPrajitura(7,"Ecler",3.4f));
    InsertNode(&tree, newPrajitura(4,"Savarina",8.7f));
    InsertNode(&tree, newPrajitura(6,"Profiterol",15.9f));
    InsertNode(&tree, newPrajitura(8,"Macarone",3.7f));
    InsertNode(&tree, newPrajitura(1,"Macarone",3.7f));
    printTreeSRD(tree);
    deleteTree(&tree);
    return 0;
}
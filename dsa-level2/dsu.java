import java.util.*;

class dsu {
    static  int find(int parent[],int x){
        if(parent[x]==x)
            return x;
        int temp=find(parent,parent[x]);
        parent[x]=temp;
        return temp;
    }
    static  int union(int pairs[][],int n){
        int parent[]=new int[n];
        int rank[]=new int[n];
        for(int i=0;i<parent.length;i++){
            parent[i]=i;
        }
        for(int val[]:pairs){
            int x=val[0];
            int y=val[1];

            int lx=find(parent,x);
            int ly=find(parent,y);

            int rx=rank[lx];
            int ry=rank[ly];

            if(rx>ry){
                parent[ly]=lx;
            }
            else if(ry>rx){
                parent[lx]=ly;
            }
            else{
                parent[ly]=lx;
                rank[lx]++;
            }


        }
        int count=0;
        for(int i=0;i<parent.length;i++){
            if(parent[i]==i)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] pairs = {
                {1, 2},
                {3, 4},
                {2, 3},
                {1, 4},
                {5, 6},
                {7, 8},
                {5, 7},
                {9, 10},
                {1, 3},
                {1, 5}
        };
        int n = 11;
        int count = union(pairs, n);
        System.out.println(count);
    }
}
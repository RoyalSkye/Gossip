
public class Antientropy1 {  //anti-entropy(push-pull)
	
	public final static int N=100;
	private double data;
	private int flag=-1;  //标记结点是否被通信过（或在数组中的位置）
	
	public double getData(){
		return this.data;
	}
	public void setData(double data){
		this.data=data;
	}
	public int getFlag(){
		return this.flag;
	}
	public void setFlag(int flag){
		this.flag=flag;
	}
	
	public static void main(String[] args){
		int count=0;  //反熵模型通信轮数
		int length1=1;  //记录node1的长度
		int test=1;
		double sum=0;
		double average;
		Antientropy1[] node=new Antientropy1[N];  //所有结点
		Antientropy1[] node1=new Antientropy1[N];  //已感染的结点
		for(int i=0;i<N;i++){  //初始化node
			node[i]=new Antientropy1();
			node[i].setData(i+1);
			sum+=node[i].getData();
		}
		average=sum/N;
		node[0].setFlag(0);
		node1[0]=new Antientropy1();
		node1[0].setData(node[0].getData());
		node1[0].setFlag(0);
		do{
			test=1;
			int length=length1;
			for(int i=0;i<length;i++){  //node1中（已感染的结点）均随机选取一个结点进行通信
				int rank=(int)(Math.random()*N);  //[0,99]
				while(rank==node1[i].getFlag()){  //不能与自己通信
					rank=(int)(Math.random()*N);  //[0,99]
				}
				if(node[rank].getFlag()==-1){  //若随机选取的结点未通信过
					node[rank].setFlag(length1);
					node1[length1]=new Antientropy1();
					double temp=(node1[i].getData()+node[rank].getData())/2;
					node1[i].setData(temp);
					node1[length1].setData(temp);
					node1[length1].setFlag(rank);
					length1++;
				}else{  //随机选取的结点通信过
					double temp=(node1[i].getData()+node1[node[rank].getFlag()].getData())/2;
					node1[i].setData(temp);
					node1[node[rank].getFlag()].setData(temp);
				}
			}
		 /*
			说明：若要当所有值均严格等于average时退出循环，则去掉下面for循环所有注释
			
			//for(int i=0;i<length1;i++){  //通信count轮后收敛于平均值
				//System.out.println("node1["+i+"]="+node1[i].getData());
				//if(node1[i].getData()==node1[0].getData()&&node1[i].getData()==average) test*=1;
				//else test*=0;
			//}
			
		*/
			for(int i=0;i<N;i++){  //所有结点都为已感染时退出循环
				if(node[i].getFlag()!=-1) test*=1;
				else test*=0;
			}
			count++;
		}while(test==0);
		System.out.println("N="+N);
		System.out.println("count="+count);
	}
}

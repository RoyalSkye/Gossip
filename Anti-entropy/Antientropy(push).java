
public class Antientropy {  //anti-entropy(push)
	
	public final static int N=100;  //结点个数
	private double data;  //结点包含的信息
	private int flag=-1;  //标记结点是否被通信过(或在数组中的位置)
	
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
		Antientropy[] node=new Antientropy[N];  //所有结点
		Antientropy[] node1=new Antientropy[N];  //已感染的结点
		for(int i=0;i<N;i++){  //初始化node
			node[i]=new Antientropy();
			node[i].setData(i+1);
		}
		node[0].setFlag(0);  //将node[0]设置为初始的已感染的结点
		node1[0]=new Antientropy();
		node1[0].setData(node[0].getData());
		node1[0].setFlag(0);
		do{
			test=1;
			int length=length1;  //length(length1)为node1 已感染的结点数组的长度
			for(int i=0;i<length;i++){  //node1中（已感染的结点）均随机选取一个结点进行通信
				int rank=(int)(Math.random()*N);  //[0,99]
				while(rank==node1[i].getFlag()){  //不能与自己通信
					rank=(int)(Math.random()*N);
				}
				if(node[rank].getFlag()==-1){  //若随机选取的结点未通信过
					node[rank].setFlag(length1);
					node1[length1]=new Antientropy();
					node1[length1].setData(node1[i].getData());
					node1[length1].setFlag(rank);
					length1++;
				}
			}
			for(int i=0;i<length1;i++){
				if(node1[i].getData()==node1[0].getData()&&node[i].getFlag()!=-1) test*=1;
				else test*=0;
				//System.out.println("node1["+i+"]="+node1[i].getData());
			}
			count++;
		}while(test==0);
		System.out.println("N="+N);
		System.out.println("count="+count);
	}
}

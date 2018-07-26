import java.util.Scanner;

public class Gossip {  //gossip
	
	public final static int N=100;
	private double data;
	private boolean infected = false;
	private boolean isolated = false;
	
	public Gossip(int i){
		this.data=i;
	}
	
	public double getData(){
		return this.data;
	}
	public void setData(double data){
		this.data=data;
	}
	public boolean isInfected() {
		return infected;
	}
	public void setInfected(boolean infected) {
		this.infected = infected;
	}
	public boolean isisolated() {
		return isolated;
	}
	public void setisolated(boolean isolated) {
		this.isolated = isolated;
	}
	
	public static void main(String[] args){
		Gossip[] nodes1=new Gossip[N];	//用于判断，存储的为当前轮开始前各结点的状态
		Gossip[] nodes2=new Gossip[N];  //用于修改，存储与当前轮同步的各结点状态
		int k = 10;  //已感染的因..变成已隔离的概率1.0/K
		int round = 0;  //轮数
		int infected = 0;  //是否已感染
		int isolated = 0;	//是否已隔离
		System.out.println("There are "+N+" nodes now, please input the amount of infected nodes:");
		Scanner s = new Scanner(System.in);  //定义初始已感染的结点数
		infected = s.nextInt();
		for(int i=0;i < N; i++){
			nodes1[i]=new Gossip(i);
			nodes2[i]=new Gossip(i);
			if(i < infected){
				nodes1[i].setInfected(true);
				nodes2[i].setInfected(true);
			}else{
				nodes1[i].setInfected(false);
				nodes2[i].setInfected(false);
			}
		}	
		for(;isolated < infected && infected < N;round++){  //当所有结点全部通信过或仅有某一小部分结点未通信，其余结点已隔离时跳出循环
			for(int i=0;i<N;i++){
				int random=(int)(Math.random()*N);  //[0,N-1]
				while(random==i){  //不能与自己通信
					random=(int)(Math.random()*N);
				}
				if(nodes1[i].isInfected()==true&&nodes1[i].isisolated()==false){  //当前结点已感染且未隔离
					if(nodes1[random].infected==true){  //随机选取的结点已感染，则当前结点有概率变为已隔离
						double random1=Math.random();
						if(random1>=0&&random1<1.0/k){  //1/k的概率不再传播该信息，变为已隔离
							nodes2[i].setisolated(true);
						}
				}else{  //随机选取的结点易受感染
					nodes2[random].setInfected(true);
				}
			}
		}
			isolated =0;
			infected=0;
			for(int j=0;j<N;j++){  //同步
				nodes1[j].setInfected(nodes2[j].isInfected());
				nodes1[j].setisolated(nodes2[j].isisolated());
				if(nodes1[j].isisolated()==true) isolated++;
				if(nodes1[j].isInfected()==true) infected++;
			}
			System.out.println("isolated="+isolated);
			System.out.println("infected="+infected);
	}
		//System.out.println("N="+N);
		System.out.println("round="+round);
}
}
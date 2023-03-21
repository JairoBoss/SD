package berkeley.monitor;


public class SimulatorMonitor
{
    private long serverTime;
    private long[] diffTimes;
    private long sumDiffs;  // Se mantiene esta variable para evitar recorrer el array de diferencias al calcular la media
    private final int numClients = 3; // 3 threads cliente con i = {0,1,2}
    private int countClientsOpered;
    
    public SimulatorMonitor(){
        this.serverTime = 0;
        this.countClientsOpered = this.numClients;
        this.diffTimes = new long[this.numClients];
        this.sumDiffs = 0;
    }
    
    public synchronized void setServerTime(long serverTime){ 
        this.serverTime = serverTime; // Se setea el tiempo actual del servidor
        try{
            notifyAll();                  // Se avisa a todos los clientes que estén esperando en la cola para poner las diferencias
            wait();                       // El servidor se pone a esperar
        }catch(InterruptedException e){}
    }
    
    public synchronized void setDiffTimes(long time,int n){
        try{
            if(serverTime==0) wait();              // Si el servidor todavía no ha configurado el tiempo los hilos cliente esperan en la cola de espera monitor
            this.diffTimes[n] = (time-serverTime); // Setea el array de diferencias
            this.sumDiffs    += time;              // Sumar a la variable sumDiffs
            countClientsOpered--;                  // Se decrementa el contador de clientes que han operado 
            //System.out.println("El cliente " + n + " setea diffTimes[n] con : " + time + "-" + serverTime + " = " + (this.diffTimes[n]));
            if(countClientsOpered==0) notify();    // Si ya han operado todos los clientes, se despierta al servidor de la cola de espera
            wait();                                // Los clientes esperan hasta que el servidor setee los ajustes de tiempo en diffTime
        }catch(InterruptedException e){}
    }
    
    public synchronized void calcAvgAndSet(){
        long avg = (this.sumDiffs / (this.numClients+1));
        for(int i=0;i<this.numClients;i++) this.diffTimes[i] = ((-this.diffTimes[i]) + avg);
        notifyAll();
    }
    
    public synchronized long getSettingTime(int n){ return this.diffTimes[n]; }
    public synchronized long getAverage()         { return this.sumDiffs / (this.numClients+1); }
    public synchronized void restartProcess(){
        this.serverTime = 0;
        this.countClientsOpered = this.numClients;
        this.sumDiffs = 0;
    }    
    
}
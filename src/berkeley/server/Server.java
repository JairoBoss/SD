package berkeley.server;

import berkeley.monitor.SimulatorMonitor;

public class Server extends Thread
{
    private SimulatorMonitor sm;
    private final int sleepMSeconds;
    private long serverTime;
    
    public Server(SimulatorMonitor sm){
        this.sm = sm;
        this.sleepMSeconds = 10000; // 10s
        this.serverTime = System.nanoTime();
    }
    
    public void run(){
        while(true){
            try{
                Thread.sleep(this.sleepMSeconds); // Duerme 10s
                // Despierta //
                /** Configura la hora del servidor en SimulatorMonitor, avisando a los clientes
                 * de que ya está configurada, el servidor se pondrá a dormir **/
                System.out.println("Temporización (servidor) : " + this.serverTime);
                this.sm.setServerTime(this.serverTime);
                /** Una vez despierte, los clientes ya habrán configurado las diferencias en el array
                 * se debe calcular la media y configurar los ajustes **/
                this.sm.calcAvgAndSet();
                /** Ajustar la hora del servidor (horaServidor + media) **/
                this.serverTime += this.sm.getAverage();
                /** Imprimir la temporización acordada **/
                System.out.println("Temporización acordada (servidor) : " + this.serverTime);           
                /** Cuando el servidor finalize se debe restaurar el estado inicial **/
                this.sm.restartProcess();
            }catch(InterruptedException e){} 
        }
    }
}

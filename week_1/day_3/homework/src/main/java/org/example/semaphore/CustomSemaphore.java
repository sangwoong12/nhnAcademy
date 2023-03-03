package org.example.semaphore;

public class CustomSemaphore{
    SharePermit permit;
    public CustomSemaphore(int permit){
        this.permit = new SharePermit(permit);
    }
    public synchronized void acquire() throws InterruptedException{
        while(permit.getPermit() == 0){
            //실행대기 시켜 다시 진입하도록
            Thread.yield();
        }
        permit.decreasePermit();
    }
    public void release(){
        permit.increasePermit();
    }
    static class SharePermit{
        int permit;

        public SharePermit(int permit) {
            this.permit = permit;
        }

        public int getPermit() {
            return permit;
        }

        public void increasePermit(){
            ++this.permit;
        }
        public void decreasePermit(){
            --this.permit;
        }
    }
}

public class Box <T>{
    private T content;

    public void pack(T content){
        this.content = content;
    }

    public T unpack(){
        return this.content;
    }
}

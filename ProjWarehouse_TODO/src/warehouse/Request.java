package warehouse;

public class Request {
    private int [] request;

    public Request(int[] request) {
        this.request = request;
    }

    @Override
    public String toString() {
        StringBuilder str= new StringBuilder("");
        for (int i = 0; i <request.length ; i++) {
            str.append(request[i]).append(" ");
        }
        return str.toString();
    }


    public int[] getRequest() {
        return request;
    }


}

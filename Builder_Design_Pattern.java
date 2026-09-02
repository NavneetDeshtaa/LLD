
class httpRequest{
    
    private String url;
    private String method;
    private String body;
    
    private httpRequest(){}
    
    public void execute(){
        if(url != null){System.out.println("URL :" + url);}
        if(method != null){System.out.println("Method :" + method);}
        if(body != null){System.out.println("Body :" + body);}
    }
    
    static class Builder{
        private httpRequest req;
        
        public Builder(){
            req = new httpRequest();
        }
        
        public Builder withUrl(String url){
            req.url = url;
            return this;
        }
        
        public Builder withMethod(String method){
            req.method = method;
            return this;
        }
        
        public Builder withBody(String body){
            req.body = body;
            return this;
        }
        
        public httpRequest build(){
            return req;
        }
        
    }
    
}


public class Main{
    
    public static void main(String[] args){
        
        httpRequest Request1 = new httpRequest.Builder()
                               .withUrl("https://www.NavneetDeshtaProgrammer.com")
                               .withMethod("POST")
                               .withBody("Hello I am body")
                               .build();
                             
        Request1.execute();
        
    }
}




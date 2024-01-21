const {createApp} = Vue
    createApp({
    	data(){
    		return {
    			// 멤버변수 
    			fd:'마포',
    			find_data:[],
    			food_detail:{}
    		}
    	},
    	// $(function(){}) => window.onload
    	mounted(){
    		axios.get('http://localhost:8080/web/food/find_vue.do',{
    			params:{
    				fd:this.fd,
    				page:1
    			}
    		}).then(response=>{
    			this.find_data=response.data
    			console.log(response.data)
    		})
    	},
    	methods:{
    		find(){
    			axios.get('http://localhost:8080/web/food/find_vue.do',{
        			params:{
        				fd:this.fd,
        				page:1
        			}
        		}).then(response=>{
        			this.find_data=response.data
        			console.log(response.data)
        		})
    		},
    		detail(fno){
    			axios.get('http://localhost:8080/web/food/detail_vue.do',{
        			params:{
        				fno:fno
        			}
        		}).then(response=>{
        			this.food_detail=response.data
        			console.log(response.data)
        		})
    		}
    	}
    }).mount('.container')
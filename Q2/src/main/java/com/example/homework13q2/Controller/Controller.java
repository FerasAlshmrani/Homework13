package com.example.homework13q1.Controller;


import com.example.homework13q1.ApiResponse.ApiResponse;
import com.example.homework13q1.TaskTracker.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/api/v1/task")
@RestController
public class Controller {

    ArrayList<TaskTracker> tasks = new ArrayList<>();


    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody TaskTracker task){
        tasks.add(task);
        return new ApiResponse("Task Added",200);
    }

    @GetMapping("/display")
    public ArrayList<TaskTracker> getTask (){
        return tasks;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index ,@RequestBody TaskTracker task){
        if(tasks.size() == 0){
            return new ApiResponse("you can NOT update because array is empty;",100);
        } else if(index < 0 ){
            return new ApiResponse("you can NOT put number less than 0 ",100);
        } else if (tasks.size() < index) {
            return new ApiResponse("you can NOT put number more than size",100);
        }else{
            tasks.set(index,task);
            return new ApiResponse("Updated",200);
        }
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse updateTask(@PathVariable int index) {
        if (tasks.size() == 0) {
            return new ApiResponse("you can NOT update because array is empty;", 100);
        } else if (index < 0) {
            return new ApiResponse("you can NOT put number less than 0 ", 100);
        } else if (tasks.size() < index) {
            return new ApiResponse("you can NOT put number more than size", 100);
        } else {
            tasks.remove(index);
            return new ApiResponse("Updated", 200);
        }
    }
        @PutMapping("/change-status/{index}")
        public ApiResponse changeStatus(@PathVariable int index,TaskTracker task){

        //check array if 0 and index out of bounds
            if(tasks.size() == 0){
                return new ApiResponse("you can NOT update because array is empty;",100);
            } else if(index < 0 ){
                return new ApiResponse("you can NOT put number less than 0 ",100);
            } else if (tasks.size() < index) {
                return new ApiResponse("you can NOT put number more than size",100);
            }else{

                // change status
                task = tasks.get(index);
                if(task.getStatus().equals("done")){
                    task.setStatus("not done");
                    return new ApiResponse("Changed from 'done' to 'not done'",200);
                } else if (task.getStatus().equals("not done")) {
                    task.setStatus("done");
                    return new ApiResponse("Changed from 'not done' to 'done'",200);
                }else {
                    return new ApiResponse("failed didnt found done or not done",300);
                }
            }
        }

        // IMPORTANT NOTE
        // In the postman just write in @Requestbody without {} just write what you want to search and without " "

    @GetMapping("/search-title")
    public ApiResponse getTitle(@RequestBody String title ,TaskTracker task){
        String checkTitle = "";
        boolean found = false;
        int index = 0 ;
        for(int i = 0 ; i<tasks.size();i++){
            task = tasks.get(i);
            checkTitle = task.getTitle();
            if(checkTitle.equals(title)){
                found = true;
                index = i ;
                break;
            }
        }
        if(found == true){
            return new ApiResponse("Found index number : "+index,200);
        }else{
            return new ApiResponse("Not found",200);
        }
    }



}





package com.example.safproject;

import java.io.File;

public class Method {
    public static void load_Directory_Files(File directory){
        File[] fileList = directory.listFiles();
        if(fileList != null && fileList.length > 0){
            for (File file : fileList) {
                if (file.isDirectory()) {
                    load_Directory_Files(file);
                } else {
                    String name = file.getName().toLowerCase();
                    for (String extension : Constant.videoExtensions) {
                        //check the type of file
                        if (name.endsWith(extension)) {
                            Constant.allMediaList.add(file);
                            //when we found file
                            break;
                        }
                    }
                }
            }
        }
    }
}

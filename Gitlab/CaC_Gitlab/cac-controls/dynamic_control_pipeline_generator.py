#!/usr/bin/env python3

import os
import sys
from pathlib import Path
from shutil import copyfile
Orchestrator_control_path ="cac-controls/gitlab"
orchstrfile = "controlsexecutor.gitlab-ci.yml"
controlfiletype = "gitlab-ci.yml"

def get_caccontrols():
    #Get List of cac control files committed from repo path passed as argument to script     
    control_changed_dir = sys.argv[1:]
    print(control_changed_dir)
    new_control_changed_dir=[]
    for newcontrol in control_changed_dir:
        new_control_changed_dir.append("cac-controls/controls/"+newcontrol)
    return new_control_changed_dir  
    
def generate_childpipeline_config():
    control_changed_dir = get_caccontrols()
    #define path of orchestrator control file stored on patters/**/gitlab/
    control_filepath = os.path.join(Orchestrator_control_path,orchstrfile)    
    with open(control_filepath,'a') as contents:
        contents.write("\ninclude:\n")  #Append the control file with "ïnclude"
    for controllist in control_changed_dir:
        #for the each changed file get the directory path
        #control_dir = os.path.dirname(controllist)
        print("cac_control_dir path is:" +controllist)
        print(os.listdir(controllist))
        #from the control change folders look for the orchestrator yml pipeline file
        try:
            for control in os.listdir(controllist):
#                print(control)
                if control.endswith(controlfiletype):                    
                    control_childpipeline_path = os.path.join(controllist,control) 
                    print("Orchestrator file path :" +control_childpipeline_path)    
                    # in include section append each orchestration file in folder where cac controls are changed by users       
                    with open(control_filepath,'a') as include_file_contents:
                        include_file_contents.write("- local: "+control_childpipeline_path+"\n") 
  
        except:
            print("An exception occurred")
      
                  
        

if __name__ == "__main__":
    generate_childpipeline_config()

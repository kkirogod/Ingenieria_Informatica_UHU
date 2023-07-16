
# PlanAhead Launch Script for Post-Synthesis pin planning, created by Project Navigator

create_project -name Practica_6 -dir "D:/UHU/FC/Practica 6 FC/planAhead_run_1" -part xc3s100evq100-4
set_property design_mode GateLvl [get_property srcset [current_run -impl]]
set_property edif_top_file "D:/UHU/FC/Practica 6 FC/Practica_6.ngc" [ get_property srcset [ current_run ] ]
add_files -norecurse { {D:/UHU/FC/Practica 6 FC} }
set_param project.pinAheadLayout  yes
set_property target_constrs_file "Practica_6.ucf" [current_fileset -constrset]
add_files [list {Practica_6.ucf}] -fileset [get_property constrset [current_run]]
link_design

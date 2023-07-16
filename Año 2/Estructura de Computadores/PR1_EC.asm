.MODEL SMALL
.STACK 100h 

.DATA 

  CADENA      DB 5,0,0,0,0,0,0
  PESO        DB 4,2,1
  VALOR_C1    DB 0
  VALOR_C2    DB 0
  SIGNO_C1    DB '+'
  SIGNO_C2    DB '+' 

.CODE 

  MOV AX, SEG CADENA
  MOV DS, AX

  ;capturar cadena de caracteres por teclado
  MOV DX, Offset CADENA 
  MOV AH, 0Ah 
  INT 21h
  
  ;cambiar caracteres ASCII por los numericos correspondientes
  SUB CADENA[2], 48
  SUB CADENA[3], 48
  SUB CADENA[4], 48
  SUB CADENA[5], 48               

  ;calculamos el complemento a 1
  ;comprobamos si es negativo
  CMP CADENA[2], 1
  JE ESNEG;
     
  ;suponemos que es positivo 
  MOV BX, 0                       ;inicializamos el registro BX  
  MOV AL, CADENA[3]
  MUL PESO[1]                     ;PREGUNTAR SI ESTÁ BIEN
  ADD BX, AX                      ;sumamos lo que se ha almacenado en el reg. acumulador(AX)
                         
  MOV AL, CADENA[4]
  MUL PESO[2]                     
  ADD BX, AX

  MOV AL, CADENA[5]
  MUL PESO[3]                     
  ADD BX, AX

  JMP FINC1 
    
  ;suponemos que es negativo 
  ESNEG: 

  MOV BX, 0 
  MOV AL, CADENA[3]
  NOT AL                           ;invertimos el bit
  MUL PESO[1]    
  ADD BX, AX
  
  MOV BX, 0 
  MOV AL, CADENA[4]
  NOT AL                          
  MUL PESO[2]    
  ADD BX, AX

  MOV BX, 0 
  MOV AL, CADENA[5]
  NOT AL                          
  MUL PESO[3]    
  ADD BX, AX

  MOV SIGNO_C1, '-' 


  FINC1:
  MOV VALOR_C1, BX                 ;PREGUNTAR SI ESTÁ BIEN


  ;calculamos el complemento a 2

        
        
        



      
  ;mostrar por pantalla los valores(+48)
    
     
     
     

     
     
     

  MOV AH, 4Ch
  INT 21h

END
TITLE Flappy Bird Game

; Program Description: Flappy Bird Game
; Author: Bilal Zaib
; Creation Date: 12/5/2015

;--------------------------------------------------------------------------------------------------

INCLUDE Irvine32.inc
INCLUDE Macros.inc
INCLUDELIB User32.lib
INCLUDE     GraphWin.inc
Include file.asm
Include Load.asm

VK_UP		EQU		000000026h

GetKeyState PROTO, nVirtKey:DWORD

.data
	;when user enter fot play again then all data will be initialized 
	
	titleStr   BYTE "FLAPPY BALL",0
	
	cursorInfo CONSOLE_CURSOR_INFO <>
	
	col BYTE 10
		
    	row BYTE 6
    	
	SPACE BYTE 6				;Space in Hurdles
	
	SET BYTE 25,50,75			;Position of Walls 
	
	CLEAROLS BYTE 26,51,76			;Position to clear wall
	
	HURD BYTE 8,5,3			;Position in wall where space is created 
	
	SCORE BYTE 0
	
	LEVEL BYTE 0
	
;------------------------------------------------------------------------------------------------------
.code
;Procedure to initialize values
SetVal PROC
	mov esi,0
	mov al,25
	mov ecx,3
	LL1:
		mov SET[esi],al
		inc al
		mov CLEAROLS[esi],al
		dec al
		add al,25
		inc esi
		push eax
		mov eax,6			;To create Random value for hurdle
		call	RandomRange
		add eax,2
		mov HURD[esi],al
		pop eax
	LOOP LL1
ret
SetVal ENDP

;Procedure to print hurdles for Flappy Bird
Print PROC
call	Randomize
mov esi,0
;for outer loop
	.WHILE esi<3				;outer loop
	mov dh,1				;set Y_AXIS
		.WHILE dh < 19 			;Inner Loop
			.IF dh == HURD[esi]	
			add dh,SPACE		;Space in Hurdles
			.ENDIF
		mov dl,SET[esi]			;Set X_AXIS
		call	GOTOXY			;Set curser on given location
		push edx			;To save Y_AXIS
		mov eax,4Ah
		call SetTextColor		;set background && Text color
		
		mov al,"x"			;Print Wall
		call writechar
		pop edx
		INC dh				;Increment in Y_AXIS
		.ENDW
	.IF SET[esi]<=0
		mov eax,6			;To create Random value for hurdle
		call	RandomRange
		add eax,2
		mov HURD[esi],al
		mov SET[esi],75			;Set new hurdle
		INC SCORE			;Increament in Score
		.ENDIF
	DEC SET[esi]
	INC esi
	.ENDW
ret
Print ENDP

;-------------------------------------------------------------------------------------------------------

;Procedure to clear hurdles for Flappy Bird
ClearOld PROC					
mov esi,0					;for outer loop
	.WHILE esi < 3				;outer loop
	mov dh,1				;set Y_AXIS
		.WHILE dh < 19 			;Inner Loop
		
		mov dl,CLEAROLS[esi]			;Set X_AXIS
		call	GOTOXY			;Set curser on given location
		push eax			;To save Y_AXIS
		
		mov eax,0fh
		call SetTextColor		;clear background
		
		mov al, ' '			;Clear Wall
		call WriteChar

		pop eax
		INC dh				;Increment in Y_AXIS
		.ENDW
	.IF CLEAROLS[esi]<=0
		mov CLEAROLS[esi],75			;Set new position for hurdle
		.ENDIF
	DEC CLEAROLS[esi]
	INC esi
	.ENDW
ret
ClearOld ENDP

;---------------------------------------------------------------------------------------------

main PROC

INVOKE SetConsoleTitle, ADDR titleStr

call file1
call waitmsg

NewGame:
call Clrscr
call MouseDemo
call Clrscr
.if QtPl==1 
	call Load1
	call Clrscr
.endif
.if QtPl==2 
	JMP Quet
.endif
mov cursorInfo.bVisible,0
mov cursorInfo.dwSize,100
INVOKE SetConsoleCursorInfo, hwndConsole,
 ADDR cursorInfo
mov dh,19
mov dl,0
	.WHILE dl < 80
	mov eax,0Dh
	call SetTextColor		
	mov dh,0
	call	gotoxy
	mov al,31
	call writechar
	mov dh,19
	call	gotoxy
	mov al,30
	call writechar
	INC dl
	.ENDW
	
	mov col,10
	mov row,6
	mov SPACE,10
	mov SCORE,0
	mov LEVEL,0
	.if SCORE!=0
	mwrite"Score is not zero"
	JMP Quet
	.endif
	
	L:
		
		call	Print
		call	ClearOld

		;Check the status of key down or on
		mov ah,0
		INVOKE GetKeyState, VK_UP
		.IF ah && row > 1
			sub row,2
		.ENDIF 

		mov  dl, col        ; column
		mov  dh, row        ; row
		mov eax,09h
		call SetTextColor		;clear background
		call Gotoxy         ; Change position according to new input
			        
		mov  al, 'O'          
        	call WriteChar      ; Write point on new place
	
		;Checking Position of Ball
		.IF (row <=1 || row >=18)
			JMP Again
		.ENDIF
		mov esi,0
		.REPEAT
			mov al,HURD[esi]
			mov ah,col
			.IF (SET[esi]== ah && (row>1 && row<=al))
				JMP Again
			.ENDIF
			add al,SPACE
			DEC al
			DEC al
			.IF (SET[esi]== ah&&row>al&&row<19)
				JMP Again
			.ENDIF
			INC esi
		.UNTIL esi==3
					;Check for Spaces and Speed
		.IF SCORE>10 && SCORE<25
			invoke Sleep, 75
			mov LEVEL,1
			mov SPACE,9
		.ELSEIF SCORE>=25 && SCORE<45
			invoke Sleep, 50
			mov LEVEL,2
			mov SPACE,8
		.ELSEIF SCORE>45
			invoke Sleep, 30
			mov LEVEL,3
			mov SPACE,7
		.ELSE
			invoke Sleep, 100
		.ENDIF
				    
		    
		    ; Erase Point
		    mov  dl, col        ; column
		    mov  dh, row        ; row
		    call Gotoxy         ; Change position according to new input
		    
		    mov  al,' '     
		    call WriteChar      ; Remove previous data
	
	INC row
	
					;Showing of score at particular point
	mov dh,20
	mov dl,65
	call gotoxy
	mov eax,0Eh
	call SetTextColor		;clear background
	mWrite "SCORE : "
	mov eax,0
	mov al,SCORE
	call Writedec
	
	mov dh,20
	mov dl,5
	call gotoxy
	mov eax,07h
	call SetTextColor		;clear background
	mWrite "LEVEL : "
	mov eax,0
	mov al,LEVEL
	call Writedec
			
	JMP L						;End of Game Loop
	
	Again:
		mov dh,15
		mov dl,30
		call gotoxy
		mov eax,0Ah
		call SetTextColor		;clear background
		mWrite"Game Over...!!!"
		;INVOKE Sleep,3000
		mov dh,21
		mov dl,25
		call gotoxy
		mov eax,0Ah
		call SetTextColor
		call waitmsg
		call Randomize			;Again set values for new game
		call SetVal
	JMP NewGame
Quet:	
mov dh,14
mov dl,20
call gotoxy
exit
main ENDP
END main
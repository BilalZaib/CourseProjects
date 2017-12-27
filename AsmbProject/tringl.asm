TITLE Flappy Bird Game

; Program Description: Flappy Ball Game
; Author: Bilal Zaib
; Creation Date: 12/5/2015

;--------------------------------------------------------------------------------------------------
.data 
.code

BILAL PROC, x:WORD, y:WORD
LOCAL xyPos:COORD
INVOKE GetStdHandle,STD_OUTPUT_HANDLE
	mov dx, x
	mov xyPos.x, dx
	mov dx, y
	mov xyPos.y, dx
	INVOKE SetConsoleCursorPosition, eax, xyPos
	ret
BILAL ENDP

PAGE2 PROC
	mov eax,06h
	call settextcolor
	mov al,178
	mov dl,23
	mov ecx,31
	l1:
		mov dh,6
		call gotoxy
		call writechar
		push edx
		push ecx
		push eax
		INVOKE Sleep,10
		pop eax
		pop ecx
		pop edx
		inc dl
	LOOP l1
	mov dh,6
	mov ecx,18
	l2:
		mov dl,54
		call gotoxy
		call writechar
		push edx
		push ecx
		push eax
		INVOKE Sleep,10
		pop eax
		pop ecx
		pop edx
		inc dh
	LOOP l2
	mov dl,55
	mov ecx,31
	l3:
		mov dh,22
		call gotoxy
		call writechar
		push edx
		push ecx
		push eax
		INVOKE Sleep,10
		pop eax
		pop ecx
		pop edx
		dec dl
	LOOP l3
	mov dh,22
	mov ecx,18
	l4:
		mov dl,24
		call gotoxy
		call writechar
		push edx
		push ecx
		push eax
		INVOKE Sleep,10
		pop eax
		pop ecx
		pop edx
		dec dh
	LOOP l4

	mov dh,4
	mov dl,28
	call gotoxy
	mov eax,0Ch
	call settextcolor
	mwrite"WELCOME TO FLAPPY GAME"
	
	mov eax,0F4h
	call settextcolor
	
	mov dh,10
	mov dl,30
	call gotoxy
	mwrite"|                |"
	mov dh,11
	mov dl,30
	call gotoxy
	mwrite"|                |"
	mov dh,12
	mov dl,30
	call gotoxy
	mwrite"|                |"
	
	mov dh,17
	mov dl,33
	call gotoxy
	mwrite"|          |"
	mov dh,18
	mov dl,33
	call gotoxy
	mwrite"|          |"
	mov dh,19
	mov dl,33
	call gotoxy
	mwrite"|          |"
	
	mov dl,30
	mov ecx,18
	mov al,'-'
	L:
		mov dh,9
		call gotoxy
		call writechar
		mov dh,13
		call gotoxy
		call writechar
		.if dl>32 && dl<45
		mov dh,16
		call gotoxy
		call writechar
		mov dh,20
		call gotoxy
		call writechar
		.endif
	INC dl
	LOOP L
	mov dh,11
	mov dl,36
	call gotoxy
	mov eax,0F9h
	call settextcolor
	mwrite" PLAY "

	mov dh,18
	mov dl,36
	call gotoxy
	mov eax,0F9h
	call settextcolor
	mwrite" QUIT "
	
ret
PAGE2 ENDP
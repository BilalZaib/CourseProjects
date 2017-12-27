.data
.code
Load1 PROC

	mov dh,8
	mov dl,25
	call gotoxy
	mov eax,0Ah
	call SetTextColor
	mWrite"LOADING...!!!"
	mov eax,0Eh
	call SetTextColor
	mov dl,25
	mov ecx,30
	mov al,'-'
	L:
		mov dh,10
		call gotoxy
		call writechar
		mov dh,12
		call gotoxy
		call writechar
		inc dl
	LOOP L
	mov eax,0Dh
	call SetTextColor
	mov ecx,30
	mov dl,25
	mov al,178
	L1:
		mov al,178
		mov dh,11
		call gotoxy
		call writechar
		push edx
		push ecx
		INVOKE Sleep,150
		pop ecx
		pop edx
	INC dl
	LOOP L1
	
	mov edx,1600h
	call gotoxy
ret
Load1 ENDP
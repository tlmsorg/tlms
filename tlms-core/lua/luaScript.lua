function  hi(some,nextVar)
		myTable = {}
		myTable[1]="Lua"
		myTable["wow"]="wow"
		-- print("表格1：",myTable[1])
		
		myTable[0] = {"aa","bb","cc"}
		myTable[1] = {"aa","bb","cc"}
		myTable[2] = {"d","e","f"}
		
		for i = 0,3 do
			myTable[i] = {"aa"..i.."TOM","bb"..i,"cc"..i};
			print("构建行myTable["..i.."]",myTable[i])
		end
	
		if(some == "brighttang")
		then
			return myTable
		else
			return myTable
		end
end		
		

hTable={
    [1]={"a","b","c"},
    [2]={"d","e","f"}
}
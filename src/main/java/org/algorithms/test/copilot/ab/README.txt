Run these one by one in any Windows or Bash terminal or double click on build_and_run.bat file (for Windows)

javac --release 17 -d out ABStringSolution.java
jar cfm ABStringSolution.jar manifest.txt -C out . ABStringSolution.java README.txt
java -Xmx128M -jar ABStringSolution.jar

Example

ababaabbbaaababbaab

a b a b a a b b b  a  a  a  b  a  b  b  a  a  b
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19

1 1 2 2 3 4 4 4 4  5  6  7  7  8  8  8  9 10 10  a
0 1 1 2 2 2 3 4 5  5  5  5  6  6  7  8  8  8  9  b

3 10 6 -> 4
lb+k-1=3+6-1=8=n -> s[n]=s[8]=b -> t=b[lb-1]=b[3-1]=b[2]=1 -> b[n]=b[8]=4 -> u=b[n]-t=b[8]-1=4-1=3 -> p=a[lb-1]=a[3-1]=1 -> z=p+u=1+3=4 -> z=4=a[6]=a[7]=a[8] -> m={6,7,8}
v=min(6,7,8)=6 -> v<=rb-lb+1 -> v<=10-3+1 -> 6<=8 -> x=v-lb+1=6-3+1=4

4 18 9 -> 10
lb+k-1=4+9-1=12=n -> s[n]=s[12]=a -> t=a[lb-1]=a[4-1]=a[3]=2 -> a[n]=a[12]=7 -> u=a[n]-t=a[12]-2=7-2=5 -> p=b[lb-1]=b[4-1]=1 -> z=p+u=1+5=6 -> z=6=b[13]=b[14] -> m={6,7,8}
v=min(13,14)=13 -> v<=rb-lb+1 -> v<=18-4+1 -> 13<=13 ->  x=v-lb+1=13-4+1=10

1 9 8 -> 6
lb+k-1=1+8-1=8=n -> s[n]=s[8]=b -> lb=1 -> t=0 -> b[n]=b[8]=4 -> u=b[n]-t=4-0=4 -> lb=1 -> p=0 -> z=p+u=4 -> z=4=a[6]=a[7]=a[8] -> m={6,7,8} ->
v=min(6,7,8)=6 -> v<=rb-lb+1 -> v<=9-1+1 -> 9<=9 -> x=v-lb+1=6-1+1=6

1 9 9 -> -1
lb+k-1=1+9-1=9=n -> s[n]=s[9]=b -> lb=1 -> t=0 -> b[n]=b[9]=5 -> u=b[n]-t=5-0=5 -> lb=1 -> p=0 -> z=p+u=5 -> z=5=a[10] -> m={10} ->
v=min(10)=10 -> v<=rb-lb+1 -> v<=9-1+1 -> 10<=9 -> false -> x=-1

2 6 5 -> -1
7 10 3 -> -1
1 3 1 -> 2
import itertools
A = list(itertools.permutations(range(1,7)))

def troublesort(L):
    done = False
    while done is False:
        done = True
        for i in range(0,len(L)-2):
            if L[i] > L[i+2]:
                done = False
                t = L[i+2]
                L[i+2] = L[i]
                L[i] = t



for i in A:
    L = list(i)
    LO = list(i)
    troublesort(L)
    if sorted(L) != L:
        print(LO)
        print(L)
        print("***")


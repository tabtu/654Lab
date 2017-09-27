package uow.acc.tab.asmt1.hashTable;


public interface HashFamily<AnyType>
{
    int hash( AnyType x, int which );
    int getNumberOfFunctions( );
    void generateNewFunctions( );
}

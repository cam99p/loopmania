package unsw.loopmania;

public abstract class Goal {
    public Boolean checkCompleted(LoopManiaWorld world){
        return true; //This should never be used. Should be done by subclasses instead\
    }
    //This abstract class forms the chassis for the goals composite design
    //All goals are either AND, OR or SINGLE, with SINGLE goals forming the leaves
}

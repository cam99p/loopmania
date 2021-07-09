package unsw.loopmania;

public interface Attack {
    public void AttackTarget (MovingEntity target, int seed); 
    //The seed is for attacks with a random effect,
        //eg trance, zombification or vampire frenzy
}

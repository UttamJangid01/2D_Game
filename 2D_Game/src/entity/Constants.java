package entity;

// For player
public class Constants{    
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int RUN = 2;
        public static final int RUN_ATTACK = 3;
        public static final int ATTACK_1 = 4;
        public static final int ATTACK_2 = 5;
        public static final int ATTACK_3 = 6;
        public static final int JUMP = 7;
        public static final int HURT = 8;
        public static final int DEAD = 9;
        public static final int HANG = 10;
        public static final int PULL_UP = 11;
        public static final int ROLL = 12;
        public static final int CLIMB = 13;
        public static final int CIRCLE_KICK = 14;
        public static final int ELIXIR = 15;
        //public static final int SPECIAL_ATTACK = 16;
        public static final int PROTECT = 16;
        public static final int TAKE = 17;
        public static final int REST = 18;

        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case PROTECT :
                case HURT : return 2;
                case TAKE :
                case REST :
                case RUN_ATTACK : 
                case ATTACK_1 : 
                case ATTACK_2 : 
                case ATTACK_3 : 
                case DEAD : return 4;
                case JUMP : 
                case ELIXIR :
                case PULL_UP : return 5;
                case IDLE :
                case HANG :
                case ROLL :
                case CLIMB :
                case RUN : return 6; 
                case CIRCLE_KICK : return 7;
                case WALK : return 8;
                default: return 1;
            }
        }
    }

    // For enemys
    public static class Enemy01Constants{
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int ATTACK = 2;
        public static final int HURT = 3;
        public static final int DEAD = 4;

        public static int E01_GetSpriteAmount(int enemy_action){
            switch(enemy_action){
                case DEAD : return 3;
                case HURT : return 4;
                case WALK : return 5;
                case IDLE :
                case ATTACK : return 6;
                default : return 1;
            }
        }

        public static int E02_GetSpriteAmount(int enemy_action){
            switch(enemy_action){
                case DEAD : 
                case HURT : return 3;
                case ATTACK : return 5;
                case IDLE :return 6;
                case WALK : return 8;
                default : return 1;
            }
        }
    }
}


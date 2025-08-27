import javax.swing.JOptionPane;


abstract class Character {
    protected String name;
    protected int hp = 100;
    protected int attackDamage;

    public Character(String name) {
        this.name = name;
    }

    public abstract int attack();  
    public abstract int special(); 
    public abstract int secret(); 

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0; 
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}


class Mage extends Character {
    public Mage(String name) {
        super(name);
    }

    @Override
    public int attack() {
        return 20; 
    }

    @Override
    public int special() {
        return 25; 

    @Override
    public int secret() {
        return 35; 
    }
}


class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    @Override
    public int attack() {
        return 15; 
    }

    @Override
    public int special() {
        return 20; 
    }

    @Override
    public int secret() {
        return 30; 
    }
}

public class BattleGame {
    public static void main(String[] args) {
        
        String mageName = JOptionPane.showInputDialog("Enter Mage's name:");
        String warriorName = JOptionPane.showInputDialog("Enter Warrior's name:");

        Mage mage = new Mage(mageName);
        Warrior warrior = new Warrior(warriorName);

        JOptionPane.showMessageDialog(null, 
            "Game starts!\n" + mage.getName() + " vs " + warrior.getName() + 
            "\nBoth have 100 HP!");

        
        while (mage.isAlive() && warrior.isAlive()) {
            
            String mageChoice = JOptionPane.showInputDialog(
                mage.getName() + "'s turn!\nChoose move:\n1. Fireball (20)\n2. Lightning Strike (25)\n3. Secret Power (35)"
            );
            int mageDamage = 0;
            if ("1".equals(mageChoice)) {
                mageDamage = mage.attack();
                JOptionPane.showMessageDialog(null, mage.getName() + " casts Fireball! (" + mageDamage + " damage)");
            } else if ("2".equals(mageChoice)) {
                mageDamage = mage.special();
                JOptionPane.showMessageDialog(null, mage.getName() + " strikes with Lightning! (" + mageDamage + " damage)");
            } else {
                mageDamage = mage.secret();
                JOptionPane.showMessageDialog(null, mage.getName() + " unleashes Secret Power! (" + mageDamage + " damage)");
            }
            warrior.takeDamage(mageDamage);

            
            if (!warrior.isAlive()) break;

            
            String warriorChoice = JOptionPane.showInputDialog(
                warrior.getName() + "'s turn!\nChoose move:\n1. Sword Slash (15)\n2. Shield Bash (20)\n3. Berserk Strike (30)"
            );
            int warriorDamage = 0;
            if ("1".equals(warriorChoice)) {
                warriorDamage = warrior.attack();
                JOptionPane.showMessageDialog(null, warrior.getName() + " slashes with sword! (" + warriorDamage + " damage)");
            } else if ("2".equals(warriorChoice)) {
                warriorDamage = warrior.special();
                JOptionPane.showMessageDialog(null, warrior.getName() + " uses Shield Bash! (" + warriorDamage + " damage)");
            } else {
                warriorDamage = warrior.secret();
                JOptionPane.showMessageDialog(null, warrior.getName() + " goes Berserk! (" + warriorDamage + " damage)");
            }
            mage.takeDamage(warriorDamage);

            // Show HP status
            JOptionPane.showMessageDialog(null, 
                "HP Status:\n" + 
                mage.getName() + " (Mage): " + mage.getHp() + " HP\n" +
                warrior.getName() + " (Warrior): " + warrior.getHp() + " HP"
            );
        }

        // Announce winner
        String winner = mage.isAlive() ? mage.getName() : warrior.getName();
        JOptionPane.showMessageDialog(null, "🎉 " + winner + " wins the battle!");
    }
}


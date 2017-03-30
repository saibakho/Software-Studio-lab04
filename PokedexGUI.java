//package com.sslab.pokemon;

//import com.sslab.pokemon.sprite.PokemonSprite;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.lang.NullPointerException;

/**
 * Created by jerry on 2017/3/19.
 */
public class PokedexGUI {
    private JComboBox<String> speciesComboBox;
    private JPanel root;
    private JLabel imageLabel;
    // stat
    private JLabel hpLabel;
    private JLabel attackLabel;
    private JLabel defenseLabel;
    private JLabel spatkLabel;
    private JLabel spdefLabel;
    private JLabel speedLabel;

    private JLabel nameLabel;
    private JLabel typeLabel;
    //
    private ArrayList<JLabel> statLabels;
    Pokedex pokedex;

    public PokedexGUI() {
        pokedex = new Pokedex("pokemonData.json");
        statLabels = new ArrayList<>();
        PokemonSpeciesData initPokemon = pokedex.getPokemonData(0);
        // stats
    //=========================================================================
        hpLabel     = new JLabel();     statLabels.add(hpLabel);
        attackLabel = new JLabel();     statLabels.add(attackLabel);
        defenseLabel= new JLabel();     statLabels.add(defenseLabel);
        spatkLabel  = new JLabel();     statLabels.add(spatkLabel);
        spdefLabel  = new JLabel();     statLabels.add(spdefLabel);
        speedLabel  = new JLabel();     statLabels.add(speedLabel);
        JLabel hp       = new JLabel("HP      : ");     statLabels.add(hp);
        JLabel attack   = new JLabel("Attack  : ");     statLabels.add(attack);
        JLabel defense  = new JLabel("Defense : ");     statLabels.add(defense);
        JLabel sp_atk   = new JLabel("SP.ATK  : ");     statLabels.add(sp_atk);
        JLabel sp_def   = new JLabel("SP.DEF  : ");     statLabels.add(sp_def);
        JLabel speed    = new JLabel("Speed   : ");     statLabels.add(speed);
        for (int i = 0; i < 12; i++) {
            if (i < 6) {
                statLabels.get(i).setBounds(320+(((i)<3)?0:130), 50+(i%3)*40, 30, 30);
                statLabels.get(i).setText("" + initPokemon.getSpeciesValue().getValArray()[i]);
            } else
                statLabels.get(i).setBounds(220+(((i-6)<3)?0:130), 50+(i%3)*40, 100, 30);
            statLabels.get(i).setFont(new Font("Consolas", Font.BOLD, 16));
        }    
        // label
    //=========================================================================
        imageLabel = new JLabel();
        imageLabel.setBounds(70, 50, 30, 30);
        imageLabel.setIcon(new ImageIcon(PokemonSprite.getSprite(0)));

        nameLabel = new JLabel("Name : " + initPokemon.getSpeciesName());
        nameLabel.setBounds(10, 90, 200, 30);
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        typeLabel = new JLabel("Type : " + initPokemon.getType());
        typeLabel.setBounds(10, 130, 200, 30);
        typeLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        // comboBox
    //=========================================================================
        speciesComboBox = new JComboBox<>();
        speciesComboBox.setBounds(10, 10, 200, 30);
        speciesComboBox.setFont(new Font("Consolas", Font.BOLD, 16));
        for (int i = 0; i < pokedex.getPokemonSize(); i++)
            speciesComboBox.addItem(pokedex.getPokemonData(i).getId() + " : " + pokedex.getPokemonData(i).getSpeciesName());
        speciesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO update fields when select items in combobox
                int curIndex = speciesComboBox.getSelectedIndex();
                PokemonSpeciesData curPokemon = pokedex.getPokemonData(curIndex);
                nameLabel.setText("Name : " + curPokemon.getSpeciesName());
                typeLabel.setText("Type : " + curPokemon.getType());
                imageLabel.setIcon(new ImageIcon(PokemonSprite.getSprite(curIndex)));
                for (int i = 0; i < 6; i++)
                    statLabels.get(i).setText(""+curPokemon.getSpeciesValue().getValArray()[i]);

            }
        });
    }
    //set the icon of a label of pokemon according to the id
    private void setPokemonIcon(int id,JLabel label)
    {
        ImageIcon icon = new ImageIcon(PokemonSprite.getSprite(id));
        label.setIcon(icon);
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("PokedexGUI");
        PokedexGUI init = new PokedexGUI();
        frame.setSize(510, 160 + 60);
        frame.setLayout(null);
        frame.setVisible(true);
        // add components
        frame.add(init.speciesComboBox);
        frame.add(init.imageLabel);
        frame.add(init.nameLabel);    
        frame.add(init.typeLabel);
        for (int i = 0; i < 12; i++)
            frame.add(init.statLabels.get(i));
    }
}


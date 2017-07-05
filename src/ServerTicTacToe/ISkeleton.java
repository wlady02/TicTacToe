package ServerTicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface ISkeleton {
    public int getIid();
    public void process(DataInputStream canalEntrada,DataOutputStream canalSalida);
}
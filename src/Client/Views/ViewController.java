package Client.Views;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;

public interface ViewController
{
  void init(ViewHandler vh, ViewModelFactory vmf);
}

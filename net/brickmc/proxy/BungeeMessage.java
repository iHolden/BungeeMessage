package net.brickmc.proxy;
 
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
 
public class BungeeMessage extends Plugin
  implements Listener
{
       
        private Map<String, String> messagers = new HashMap<String, String>();
       
  public void onEnable()
  {
    getProxy().getPluginManager().registerListener(this, this);
    getProxy().getPluginManager().registerCommand(this, new Command("msg")
    {
        @SuppressWarnings("deprecation")
		public void execute(CommandSender sender, String[] args) {
                  if (args.length == 0) {
                          sender.sendMessage(ChatColor.RED + "Usage: /msg <player> <message>");
                  } else if (args.length == 1) {
                          sender.sendMessage(ChatColor.RED + "Usage: /msg <player> <message>");
                  } else {
                         
                          if (!(sender instanceof ProxiedPlayer)) {
                                 
                        	  if (args[0].equalsIgnoreCase("proxy")) {
                        		  
                        		  sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot send a message to yourself");
                        		  
                        	  } else {
                        		  
                        		  StringBuilder str = new StringBuilder();
                                  for (int i = 1; i < args.length; i++) {
                                          str.append(args[i] + " ");
                                  }
                                  String nmessage = str.toString();
                                  String message = nmessage.replace("&", "§");
                                  ProxiedPlayer p1 = getProxy().getPlayer(args[0]);
                                  if (p1 != null) {
                                  sender.sendMessage(ChatColor.LIGHT_PURPLE + "To " + args[0] + ": " + ChatColor.RESET + message);
                                          p1.sendMessage(ChatColor.LIGHT_PURPLE + "From proxy: " + ChatColor.RESET + message);
                                        messagers.put(p1.getName(), "proxy");
                                        messagers.put("proxy", p1.getName());
                                  } else {
                                          sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Could not find player " + args[0]);
                                  }
                        		  
                        	  }
                                 
                          } else {
                                 
                                  if (args[0].equals(sender.getName())) {
                                         
                                          sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot send a message to yourself");
                                         
                                  } else {
                         
                  StringBuilder str = new StringBuilder();
                  for (int i = 1; i < args.length; i++) {
                          str.append(args[i] + " ");
                  }
                  String nmessage = str.toString();
                  String message = nmessage.replace("&", "§");
                  ProxiedPlayer p1 = getProxy().getPlayer(args[0]);
                  if (p1 != null) {
                  sender.sendMessage(ChatColor.GREEN + "To " + args[0] + ": " + ChatColor.GRAY + message);
                          p1.sendMessage(ChatColor.GREEN + "From " + sender.getName() + ": " + ChatColor.GRAY + message);
                        messagers.put(p1.getName(), sender.getName());
                        messagers.put(sender.getName(), p1.getName());
                        
                        logToConsole((ProxiedPlayer) sender, p1, message);
                  } else {
                          sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Couldn't find player " + args[0]);
                  }
                         }
                        }
                  }
          }
       
    });
    getProxy().getPluginManager().registerCommand(this, new Command("tell") {
        @SuppressWarnings("deprecation")
		public void execute(CommandSender sender, String[] args) {
                  if (args.length == 0) {
                          sender.sendMessage(ChatColor.RED + "Usage: /tell <player> <message>");
                  } else if (args.length == 1) {
                          sender.sendMessage(ChatColor.RED + "Usage: /tell <player> <message>");
                  } else {
                         
                          if (!(sender instanceof ProxiedPlayer)) {
                              
                     	  if (args[0].equalsIgnoreCase("proxy")) {
                     		  
                     		  sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot send message to yourself");
                     		  
                     	  } else {
                     		  
                     		  StringBuilder str = new StringBuilder();
                               for (int i = 1; i < args.length; i++) {
                                       str.append(args[i] + " ");
                               }
                               String nmessage = str.toString();
                               String message = nmessage.replace("&", "§");
                               ProxiedPlayer p1 = getProxy().getPlayer(args[0]);
                               if (p1 != null) {
                               sender.sendMessage(ChatColor.GREEN + "To " + args[0] + ": " + ChatColor.GRAY + message);
                                       p1.sendMessage(ChatColor.GREEN + "From Proxy: " + ChatColor.GRAY + message);
                                     messagers.put(p1.getName(), "proxy");
                                     messagers.put("proxy", p1.getName());
                                     
                                     logToConsole((ProxiedPlayer) sender, p1, message);
                               } else {
                                       sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Could not find player " + args[0]);
                               }
                     		  
                     	  }
                              
                       } else {
                                 
                                  if (args[0].equals(sender.getName())) {
                                         
                                          sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot send message to yourself");
                                         
                                  } else {
                         
                  StringBuilder str = new StringBuilder();
                  for (int i = 1; i < args.length; i++) {
                          str.append(args[i] + " ");
                  }
                  String nmessage = str.toString();
                  String message = nmessage.replace("&", "§");
                  ProxiedPlayer p1 = getProxy().getPlayer(args[0]);
                  if (p1 != null) {
                  sender.sendMessage(ChatColor.GREEN + "To " + args[0] + ": " + ChatColor.RESET + message);
                          p1.sendMessage(ChatColor.GREEN + "From " + sender.getName() + ": " + ChatColor.RESET + message);
                        messagers.put(p1.getName(), sender.getName());
                        messagers.put(sender.getName(), p1.getName());
                        
                        logToConsole((ProxiedPlayer) sender, p1, message);
                  } else {
                          sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Could not find player " + args[0]);
                  }
                         }
                        }
                  }
          }
        });
    getProxy().getPluginManager().registerCommand(this, new Command("w") {
        @SuppressWarnings("deprecation")
		public void execute(CommandSender sender, String[] args) {
          if (args.length == 0) {
                  sender.sendMessage(ChatColor.RED + "Usage: /w <player> <message>");
          } else if (args.length == 1) {
                  sender.sendMessage(ChatColor.RED + "Usage: /w <player> <message>");
          } else {
                 
                  if (!(sender instanceof ProxiedPlayer)) {
                      
             	  if (args[0].equalsIgnoreCase("proxy")) {
             		  
             		  sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot send message to yourself");
             		  
             	  } else {
             		  
             		  StringBuilder str = new StringBuilder();
                       for (int i = 1; i < args.length; i++) {
                               str.append(args[i] + " ");
                       }
                       String nmessage = str.toString();
                       String message = nmessage.replace("&", "§");
                       ProxiedPlayer p1 = getProxy().getPlayer(args[0]);
                       if (p1 != null) {
                       sender.sendMessage(ChatColor.GREEN + "To " + args[0] + ": " + ChatColor.RESET + message);
                               p1.sendMessage(ChatColor.GREEN + "From proxy: " + ChatColor.RESET + message);
                             messagers.put(p1.getName(), "proxy");
                             messagers.put("proxy", p1.getName());
                             
                             logToConsole((ProxiedPlayer) sender, p1, message);
                       } else {
                               sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Could not find player " + args[0]);
                       }
             		  
             	  }
                      
               } else {
                         
                          if (args[0].equals(sender.getName())) {
                                 
                                  sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot send message to yourself");
                                 
                          } else {
                 
                StringBuilder str = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                        str.append(args[i] + " ");
                }
                String nmessage = str.toString();
                String message = nmessage.replace("&", "§");
                ProxiedPlayer p1 = getProxy().getPlayer(args[0]);
                if (p1 != null) {
                sender.sendMessage(ChatColor.GREEN + "To " + args[0] + ": " + ChatColor.RESET + message);
                  p1.sendMessage(ChatColor.GREEN + "From " + sender.getName() + ": " + ChatColor.RESET + message);
                messagers.put(p1.getName(), sender.getName());
                messagers.put(sender.getName(), p1.getName());
                
                logToConsole((ProxiedPlayer) sender, p1, message);
                } else {
                  sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Could not find player " + args[0]);
                }
                 }
                }
          }
        }
      });
    getProxy().getPluginManager().registerCommand(this, new Command("r") {
        @SuppressWarnings("deprecation")
		public void execute(CommandSender sender, String[] args) {
                if (args.length == 0) {
                       
                        sender.sendMessage(ChatColor.RED + "Usage: /r <message>");
                       
                } else {
                       
                        if (!(sender instanceof ProxiedPlayer)) {
                               
                            if (!messagers.containsKey("proxy")) {
                            	sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have nobody to reply to.");
                            	return;
                            }
                            
                            ProxiedPlayer target = getProxy().getPlayer(messagers.get("proxy"));
                            if (target == null) {
                            	sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Player not online.");
                            	return;
                            }
                            
                            StringBuilder str = new StringBuilder();
                            for (int i = 0; i < args.length; i++) {
                                    str.append(args[i] + " ");
                            }
                            String nmessage = str.toString();
                            String message = nmessage.replace("&", "§");
                           
                            sender.sendMessage(ChatColor.GREEN + "To " + target.getName() + ": " + ChatColor.RESET + message);
                            target.sendMessage(ChatColor.GREEN + "From proxy: " + ChatColor.RESET + message);
                               
                            messagers.put(target.getName(), "proxy");
                            messagers.put("proxy", target.getName());
                            
                            logToConsole((ProxiedPlayer) sender, target, message);
                               
                        } else {
                               
                    if (!messagers.containsKey(sender.getName())) {
                       
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have nobody to reply to.");
                        return;
                       
                    } else {
                    	
                    	if (messagers.get(sender.getName()).equalsIgnoreCase("proxy")) {
                    		StringBuilder str = new StringBuilder();
                            for (int i = 0; i < args.length; i++) {
                                    str.append(args[i] + " ");
                            }
                            String nmessage = str.toString();
                            String message = nmessage.replace("&", "§");
                           
                            sender.sendMessage(ChatColor.GREEN + "To proxy: " + ChatColor.RESET + message);
                            getProxy().getLogger().log(Level.INFO, ChatColor.GREEN + "From " + sender.getName() + ": " + ChatColor.RESET + message);
                               
                            messagers.put("proxy", sender.getName());
                            messagers.put(sender.getName(), "proxy");
                    		return;
                    	}
                       
                        ProxiedPlayer target = getProxy().getPlayer(messagers.get(sender.getName()));
                        if (target == null) {
                               
                                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Player not online.");
                               
                        } else {
                               
                            StringBuilder str = new StringBuilder();
                            for (int i = 0; i < args.length; i++) {
                                    str.append(args[i] + " ");
                            }
                            String nmessage = str.toString();
                            String message = nmessage.replace("&", "§");
                           
                            sender.sendMessage(ChatColor.GREEN + "To " + target.getName() + ": " + ChatColor.RESET + message);
                            target.sendMessage(ChatColor.GREEN + "From " + sender.getName() + ": " + ChatColor.RESET + message);
                               
                            messagers.put(target.getName(), sender.getName());
                            messagers.put(sender.getName(), target.getName());
                            
                            logToConsole((ProxiedPlayer) sender, target, message);
                        }
                       
                    }
                               
                        }
                       
                }
        }
      });    
  }
  
  public void logToConsole(ProxiedPlayer player, ProxiedPlayer target, String message) {
	  getProxy().getLogger().log(Level.INFO, "*** Message *** " + player.getName() + " to " + target.getName() + " >> " + message);
  }
}

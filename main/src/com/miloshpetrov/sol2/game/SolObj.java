package com.miloshpetrov.sol2.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.miloshpetrov.sol2.common.Nullable;
import com.miloshpetrov.sol2.game.dra.Dra;

import java.util.List;

public interface SolObj {
  void update(SolGame game);
  boolean shouldBeRemoved(SolGame game);
  void onRemove(SolGame game);
  float getRadius();
  void receiveDmg(float dmg, SolGame game, @Nullable Vector2 pos, DmgType dmgType);
  boolean receivesGravity();
  void receiveAcc(Vector2 acc, SolGame game);
  Vector2 getPos();
  FarObj toFarObj();
  List<Dra> getDras();
  float getAngle();
  Vector2 getSpd();
  void handleContact(SolObj other, ContactImpulse impulse, boolean isA, float absImpulse, SolGame game,
    Vector2 collPos);
  String toDebugString();
  Boolean isMetal();
}

package com.miloshpetrov.sol2.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.miloshpetrov.sol2.common.SolMath;

public class SolContactListener implements ContactListener {
  private final SolGame myGame;

  public SolContactListener(SolGame game) {
    myGame = game;
  }

  @Override
  public void beginContact(Contact contact) {
  }

  @Override
  public void endContact(Contact contact) {
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
    SolObj soa = ObjMan.asSolObj(contact.getFixtureA().getBody().getUserData());
    SolObj sob = ObjMan.asSolObj(contact.getFixtureB().getBody().getUserData());
    if (soa == null && sob == null) return;

    float absImpulse = calcAbsImpulse(impulse);
    Vector2 collPos = contact.getWorldManifold().getPoints()[0];
    if (soa != null) {
      soa.handleContact(sob, impulse, true, absImpulse, myGame, collPos);
    }
    if (sob != null) {
      sob.handleContact(soa, impulse, false, absImpulse, myGame, collPos);
    }
    myGame.getSpecialSounds().playColl(myGame, absImpulse, soa, collPos);
    myGame.getSpecialSounds().playColl(myGame, absImpulse, sob, collPos);
  }

  private float calcAbsImpulse(ContactImpulse impulse) {
    float absImpulse = 0;
    int pointCount = impulse.getCount();
    float[] normImpulses = impulse.getNormalImpulses();
    for (int i = 0; i < pointCount; i++) {
      float normImpulse = normImpulses[i];
      normImpulse = SolMath.abs(normImpulse);
      if (absImpulse < normImpulse) absImpulse = normImpulse;
    }
    return absImpulse;
  }
}

SolidCuboid solidcuboid = new SolidCuboid(2.0, 2.0, 2.0, 0.5)
solidcuboid.volume()
solidcuboid.mass()
Cuboid cuboid = solidcuboid
solidcuboid.volume()
solidcuboid.mass()
Shape3D shape = solidcuboid
shape.volume()
shape.mass() //error
Solid solid = solidcuboid
solid.volume()
solid.mass()
shape = cuboid
shape = solid

SolidSphere solidsphere = new SolidSphere(2.0, 0.5)
solidsphere.volume()
solidsphere.mass()
Sphere sphere = solidsphere
solidsphere.volume()
solidsphere.mass()
Shape3D shape = solidsphere
shape.volume()
shape.mass() //error
Solid solid = solidsphere
solid.volume()
solid.mass()
shape = sphere
shape = solid
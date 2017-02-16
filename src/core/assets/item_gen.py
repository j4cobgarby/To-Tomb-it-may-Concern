FILENAME = input("What's the texture name? (assets/____)\n>")
TEXT = """
{
  "version": [
    0,1
  ],
  "meshes": [
    {
      "attributes": [
        "POSITION","NORMAL","TEXCOORD0"
      ],
      "vertices": [
         1.000000, 0.000000, 1.000000,-0.000000, 1.000000,-0.000000, 0.999900, 0.999900,
        -1.000000, 0.000000,-1.000000,-0.000000, 1.000000,-0.000000, 0.000100, 0.000100,
        -1.000000, 0.000000, 1.000000,-0.000000, 1.000000,-0.000000, 0.000100, 0.999900,
         1.000000, 0.000000,-1.000000,-0.000000, 1.000000,-0.000000, 0.999900, 0.000100
      ],
      "parts": [
        {
          "id": "Plane_part0",
          "type": "TRIANGLES",
          "indices": [
            0,1,2,0,3,1
          ]
        }
      ]
    }
  ],
  "materials": [
    {
      "id": "Material.001",
      "diffuse": [
         0.800000, 0.800000, 0.800000
      ],
      "ambient": [
         0.000000, 0.000000, 0.000000
      ],
      "emissive": [
         0.800000, 0.800000, 0.800000
      ],
      "specular": [
         0.000000, 0.000000, 0.000000, 1.000000
      ],
      "reflection": [
         1.000000, 1.000000, 1.000000
      ],
      "shininess":  9.607843,
      "opacity":  0.000000,
      "textures": [
        {
          "id": "Texture",
          "filename": "%s",
          "type": "DIFFUSE"
        }
      ]
    }
  ],
  "nodes": [
    {
      "id": "Plane",
      "rotation": [
         0.707107, 0.000000,-0.000000, 0.707107
      ],
      "parts": [
        {
          "meshpartid": "Plane_part0",
          "materialid": "Material.001",
          "uvMapping": [
            [
              0
            ]
          ]
        }
      ]
    }
  ],
  "animations": []
}
""" % FILENAME

TMP = input("And the output name is? (assets/____)\n>")
OUTPUTNAME = TMP

file = open(OUTPUTNAME, "w")
file.write(TEXT)
file.close()

print("Done!\n")
input("Press ENTER to exit.")

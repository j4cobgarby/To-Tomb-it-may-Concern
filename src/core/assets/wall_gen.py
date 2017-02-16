#!/usr/bin/python3

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
        -2.000000, 2.000000, 2.000000,-1.000000, 0.000000,-0.000000, 1.000000, 0.000000,
        -2.000000,-2.000000,-2.000000,-1.000000, 0.000000,-0.000000, 0.000000, 1.000000,
        -2.000000,-2.000000, 2.000000,-1.000000, 0.000000,-0.000000, 1.000000, 1.000000,
        -2.000000, 2.000000,-2.000000, 0.000000,-0.000000,-1.000000, 1.000000, 0.000000,
         2.000000,-2.000000,-2.000000, 0.000000,-0.000000,-1.000000, 0.000000, 1.000000,
        -2.000000,-2.000000,-2.000000, 0.000000,-0.000000,-1.000000, 1.000000, 1.000000,
         2.000000, 2.000000,-2.000000, 1.000000,-0.000000,-0.000000, 1.000000, 0.000000,
         2.000000,-2.000000, 2.000000, 1.000000,-0.000000,-0.000000, 0.000000, 1.000000,
         2.000000,-2.000000,-2.000000, 1.000000,-0.000000,-0.000000, 1.000000, 1.000000,
         2.000000, 2.000000, 2.000000, 0.000000, 0.000000, 1.000000, 1.000000, 0.000000,
        -2.000000,-2.000000, 2.000000, 0.000000, 0.000000, 1.000000,-0.000000, 1.000000,
         2.000000,-2.000000, 2.000000, 0.000000, 0.000000, 1.000000, 1.000000, 1.000000,
         2.000000,-2.000000,-2.000000, 0.000000,-1.000000,-0.000000, 0.000000, 0.000000,
        -2.000000,-2.000000, 2.000000, 0.000000,-1.000000,-0.000000, 1.000000, 1.000000,
        -2.000000,-2.000000,-2.000000, 0.000000,-1.000000,-0.000000, 1.000000, 0.000000,
        -2.000000, 2.000000,-2.000000, 0.000000, 1.000000,-0.000000, 0.000000, 1.000000,
         2.000000, 2.000000, 2.000000, 0.000000, 1.000000,-0.000000, 1.000000, 0.000000,
         2.000000, 2.000000,-2.000000, 0.000000, 1.000000,-0.000000, 0.000000, 0.000000,
        -2.000000, 2.000000,-2.000000,-1.000000, 0.000000,-0.000000, 0.000000, 0.000000,
         2.000000, 2.000000,-2.000000, 0.000000,-0.000000,-1.000000, 0.000000, 0.000000,
         2.000000, 2.000000,-2.000000, 1.000000, 0.000000,-0.000000, 1.000000, 0.000000,
         2.000000, 2.000000, 2.000000, 1.000000, 0.000000,-0.000000, 0.000000, 0.000000,
         2.000000,-2.000000, 2.000000, 1.000000, 0.000000,-0.000000, 0.000000, 1.000000,
        -2.000000, 2.000000, 2.000000, 0.000000, 0.000000, 1.000000,-0.000000, 0.000000,
         2.000000,-2.000000, 2.000000, 0.000000,-1.000000,-0.000000, 0.000000, 1.000000,
        -2.000000, 2.000000,-2.000000, 0.000000, 1.000000, 0.000000, 0.000000, 1.000000,
        -2.000000, 2.000000, 2.000000, 0.000000, 1.000000, 0.000000, 1.000000, 1.000000,
         2.000000, 2.000000, 2.000000, 0.000000, 1.000000, 0.000000, 1.000000, 0.000000
      ],
      "parts": [
        {
          "id": "Cube.001_part0",
          "type": "TRIANGLES",
          "indices": [
            0,1,2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,0,18,1,3,19,4,
            20,21,22,9,23,10,12,24,13,25,26,27
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
      "id": "Cube",
      "parts": [
        {
          "meshpartid": "Cube.001_part0",
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

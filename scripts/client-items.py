import glob, json

files = glob.glob("../src/generated/resources/assets/exnihilosequentia/models/item/*.json")

for file in files:
    name = file.split('/')[-1].removesuffix('.json')
    template = {
        "model": {
            "type": "minecraft:model",
            "model": "exnihilosequentia:item/{}".format(name)
        }
    }
    # print(json.dumps(template, indent=2))
    if(name == "wooden_crook"):
        template = {
                "model": {
                    "type": "minecraft:select",
                    "property": "exnihilosequentia:holiday",
                    "fallback": {
                        "type": "minecraft:model",
                        "model": "exnihilosequentia:item/{}".format(name)
                    },
                    "cases": [
                        {
                            "when": "halloween",
                            "model": {
                                "type": "minecraft:model",
                                "model": "exnihilosequentia:item/halloween_crook"
                            },
                        },
                        {
                            "when": "christmas",
                            "model": {
                                "type": "minecraft:model",
                                "model": "exnihilosequentia:item/christmas_crook"
                            }
                        }
                    ]
                }
            }
    out = open("../src/generated/resources/assets/exnihilosequentia/items/{}.json".format(name), "w")
    out.write(json.dumps(template, indent=2))
    out.close()
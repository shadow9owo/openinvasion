    package group.ImgUi.Frames;

    import group.Data.CurrentData;
    import group.Types.Layer;
    import imgui.ImGui;

    import java.util.ArrayList;
    import java.util.List;

    import static group.Data.CurrentData.currentlayer;

    public class Layerlist {

        public static void Render() {
            ImGui.begin("Layers");

            if (CurrentData.Config.level == null) {
                ImGui.text("No level loaded");
                ImGui.end();
                return;
            }

            List<Layer> layers = CurrentData.Config.level.leveldata;

            if (ImGui.button("Add Layer")) {
                CurrentData.NEXT_LAYER_ID = CurrentData.NEXT_LAYER_ID + 1;
                layers.add(new Layer(new ArrayList<>(), CurrentData.NEXT_LAYER_ID));
                currentlayer = layers.size() - 1;
            }

            ImGui.sameLine();

            if (ImGui.button("Remove Layer")) {
                if (!layers.isEmpty() && currentlayer >= 0 && currentlayer < layers.size()) {
                    layers.remove(currentlayer);

                    if (layers.isEmpty()) {
                        currentlayer = -1;
                    } else if (currentlayer >= layers.size()) {
                        currentlayer = layers.size() - 1;
                    }
                }
            }

            ImGui.separator();

            for (int i = 0; i < layers.size(); i++) {
                Layer layer = layers.get(i);
                if (layer == null) continue;

                ImGui.pushID(i);

                boolean selected = (i == currentlayer);
                if (ImGui.selectable("Layer " + layers.get(i).layerid, selected)) {
                    currentlayer = i;
                }

                ImGui.popID();
            }

            CurrentData.Config.level.leveldata = layers;

            ImGui.end();
        }
    }

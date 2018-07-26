package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.updatechannel.Channels;
import net.enablers.tvstack.model.api.updatechannel.UpdateChannelRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelService extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ChannelService.class);


    public static Response updateChannelsToThePlan(String oktaToken, Object projectId, String channel, String buyingAudienceId, String formatId) {

        Channels channels = new Channels();
        channels.setId(channel);
        channels.setBuyingAudienceId(buyingAudienceId);
        channels.setFormatId(formatId);
        channels.setSelected(true);

        Channels[] channelsList = new Channels[1];
        channelsList[0] = channels;


        UpdateChannelRequestModel updateChannelRequestModel = new UpdateChannelRequestModel();
        updateChannelRequestModel.setProjectId(projectId.toString());
        updateChannelRequestModel.setChannels(channelsList);

        return getTvstackConfig(oktaToken).body(gson().toJson(updateChannelRequestModel)).put("/api/channels");
    }
}

<template>
    <v-card outlined>
        <v-card-title>
            CompleteDelivery
        </v-card-title>

        <v-card-text>
            <String label="DeliveryId" v-model="value.deliveryId" :editMode="editMode"/>
            <String label="Status" v-model="value.status" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="completeDelivery"
            >
                CompleteDelivery
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CompleteDeliveryCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.deliveryId = '';
            this.value.status = '';
        },
        watch: {
        },
        methods: {
            completeDelivery() {
                this.$emit('completeDelivery', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

